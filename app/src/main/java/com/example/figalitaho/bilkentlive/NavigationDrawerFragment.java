package com.example.figalitaho.bilkentlive;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link NavigationDrawerFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link NavigationDrawerFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class NavigationDrawerFragment extends Fragment implements MyRecycleViewAdapter.ClickListener{

    NavigationDrawerListener activityCommander;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    /*
    RECYCLER VIEW FOR LIST
     */
    private RecyclerView recyclerView;
    private MyRecycleViewAdapter adapter;


    /*
    TO WRITE WHAT THESE DO
     */
    public static final String PREF_FILE_NAME="testpref";
    public static final String KEY_USER_LEARNED_DRAWER = "user_learned_drawer";


    private ActionBarDrawerToggle mDrawerToggle;
    private DrawerLayout mDrawerLayout;

    private View containerView;

    private boolean mUserLearnedDrawer;
    private boolean mFromSavedInstanceState;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        if (getArguments() != null) {
//            mParam1 = getArguments().getString(ARG_PARAM1);
//            mParam2 = getArguments().getString(ARG_PARAM2);
//        }
        mUserLearnedDrawer = Boolean.valueOf(readFromPreferences(getActivity(), KEY_USER_LEARNED_DRAWER, "false"));
        if( savedInstanceState != null)
            mFromSavedInstanceState = true;
    }


    private OnFragmentInteractionListener mListener;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment NavigationDrawerFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static NavigationDrawerFragment newInstance(String param1, String param2) {
        NavigationDrawerFragment fragment = new NavigationDrawerFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public NavigationDrawerFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View layout = inflater.inflate(R.layout.fragment_navigation_drawer, container, false);
        recyclerView = (RecyclerView)layout.findViewById(R.id.drawerList);

        adapter = new MyRecycleViewAdapter(getActivity(), getData());

        //for making each item choose different activities
        adapter.setClickListener(this);

        recyclerView.setAdapter( adapter);
        recyclerView.setLayoutManager( new LinearLayoutManager(getActivity()));
        return layout;
    }

    public static List getData(){
        List<SingleRowInRecycler> data = new ArrayList<>();

        //=====================================================
        //Commented Out until features implemented


        //int[] icons = {R.drawable.ic_lectures, R.drawable.ic_events, R.drawable.ic_dinings, R.drawable.ic_transportations, R.drawable.ic_maps};
        //String[] titles = {"Lectures", "Events", "Dinings", "Transportation", "Maps"};

        //=========================================================================================

        int[] icons = {R.drawable.ic_transportations, R.drawable.ic_dinings,  R.drawable.ic_maps};
        String[] titles = {"Transportation", "Dinings",  "Maps"};

        for( int i = 0; i < titles.length && i < icons.length; i++)
        {
            SingleRowInRecycler current = new SingleRowInRecycler();
            current.setIconId(icons[i]);
            current.setTitle(titles[i]);
            data.add(current);
        }

        return data;
    }

    /**
     * This is the setUp method getting called in the main
     * @param drawerLayout
     * @param toolbar
     */
    public void setUp( int fragmentId, DrawerLayout drawerLayout, final Toolbar toolbar) {

        containerView = getActivity().findViewById(fragmentId);
        mDrawerLayout = drawerLayout;
        mDrawerToggle = new ActionBarDrawerToggle( getActivity(), drawerLayout, toolbar, R.string.drawer_open, R.string.drawer_close){

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                if( !mUserLearnedDrawer){
                    mUserLearnedDrawer = true;
                    saveToPreferences(getActivity(), KEY_USER_LEARNED_DRAWER, mUserLearnedDrawer+"");
                }
                getActivity().invalidateOptionsMenu();

            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                getActivity().invalidateOptionsMenu();
            }

            public void onDrawerSlide( View drawerView, float slideOffset){
                if( slideOffset<0.6 ){
                    toolbar.setAlpha(1-slideOffset);
                }
            }
        };
        if( !mUserLearnedDrawer && !mFromSavedInstanceState){
            mDrawerLayout.closeDrawer(containerView);
        }
        mDrawerLayout.setDrawerListener( mDrawerToggle);

        mDrawerLayout.post( new Runnable() {
            @Override
            public void run() {
                mDrawerToggle.syncState();
            }
        });
    }

    //for shared prefenreces----
    public static void saveToPreferences( Context context, String preferenceName, String preferenceValue){
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREF_FILE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(preferenceName, preferenceValue);
        editor.commit(); //didnt do apply here
    }

    //for shared prefenreces----
    public static String readFromPreferences( Context context, String preferenceName, String defaultValue){
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREF_FILE_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(preferenceName, defaultValue);
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

//    @Override
//    public void onAttach(Activity activity) {
//        super.onAttach(activity);
//        try {
//            mListener = (OnFragmentInteractionListener) activity;
//        } catch (ClassCastException e) {
//            throw new ClassCastException(activity.toString()
//                    + " must implement OnFragmentInteractionListener");
//        }
//    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onAttach(Activity activity){
        super.onAttach(activity);
        try{
            activityCommander = (NavigationDrawerListener) activity;
        } catch (ClassCastException e){
            throw new ClassCastException(activity.toString());
        }
    }



    //interface method
    @Override
    public void itemClicked(View view, int position) {
//        view.setOnTouchListener(new coordinateGiver());

        int yCoordinate;
        if( position == 0) {

//            SplitAnimation.startActivity(NavigationDrawerFragment.this, new Intent(NavigationDrawerFragment.this, Lectures.class), 0);
//            startActivity( new Intent( getActivity(), Transportations.class));
            yCoordinate = -1;
            activityCommander.transportationsClicked(yCoordinate);
//
        }
        else if ( position == 1) {

            //            SplitAnimation.startActivity(NavigationDrawerFragment.this, new Intent(NavigationDrawerFragment.this, Lectures.class), 0);
//            startActivity( new Intent( getActivity(), Dinings.class));
            yCoordinate = -1;
            activityCommander.diningsClicked(yCoordinate);


        }
        else if( position == 2) {
//            SplitAnimation.startActivity(NavigationDrawerFragment.this, new Intent(NavigationDrawerFragment.this, Lectures.class), 0);
//            startActivity( new Intent( getActivity(), Maps.class));
            yCoordinate = -1;
            activityCommander.mapsClicked(yCoordinate);


        }
        else if( position == 3) {

//            SplitAnimation.startActivity(NavigationDrawerFragment.this, new Intent(NavigationDrawerFragment.this, Lectures.class), 0);
//            startActivity( new Intent( getActivity(), Lectures.class));
            yCoordinate = -1;
            activityCommander.lecturesClicked(yCoordinate);
//
        }
        else if( position == 4) {

//            SplitAnimation.startActivity(NavigationDrawerFragment.this, new Intent(NavigationDrawerFragment.this, Lectures.class), 0);
//            startActivity( new Intent( getActivity(), Events.class));
            yCoordinate = -1;
            activityCommander.eventsClicked(yCoordinate);
        }

    }


    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        public void onFragmentInteraction(Uri uri);
    }



}
