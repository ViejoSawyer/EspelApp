package com.proyecto.albanbassante.espelapp;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.preference.PreferenceManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewTreeObserver;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.proyecto.albanbassante.espelapp.RecyclerView.Adapters.DrawerAdapter;
import com.proyecto.albanbassante.espelapp.RecyclerView.Classes.DrawerItem;
import com.proyecto.albanbassante.espelapp.RecyclerView.Utils.ItemClickSupport;

public class LoginActivity extends ActionBarActivity implements OnClickListener {


    Toolbar toolbar;
    ActionBarDrawerToggle drawerToggle;
    DrawerLayout drawerLayout;
    ToggleButton toggleButtonDrawer;
    FrameLayout statusBar, frameLayoutSetting1;
    LinearLayout linearLayoutDrawerAccount, linearLayoutDrawerMain;
    RelativeLayout relativeLayoutScrollViewChild;
    ImageView imageViewDrawerArrowUpDown;
    ScrollView scrollViewNavigationDrawerContent;
    ViewTreeObserver viewTreeObserverNavigationDrawerScrollView;
    ViewTreeObserver.OnScrollChangedListener onScrollChangedListener;
    RecyclerView recyclerViewDrawer1, recyclerViewDrawer2, recyclerViewDrawer3, recyclerViewDrawerSettings;
    RecyclerView.Adapter drawerAdapter1, drawerAdapter2, drawerAdapter3, drawerAdapterSettings;
    ArrayList<DrawerItem> drawerItems1, drawerItems2, drawerItems3, drawerItemsSettings;
    float drawerHeight, scrollViewHeight;
    LinearLayoutManager linearLayoutManager, linearLayoutManager2, linearLayoutManager3, linearLayoutManagerSettings;
    ItemClickSupport itemClickSupport1, itemClickSupport2, itemClickSupport3, itemClickSupportSettings;
    TypedValue typedValueColorPrimary, typedValueTextColorPrimary, typedValueTextColorControlHighlight, typedValueColorBackground;
    int colorPrimary, textColorPrimary, colorControlHighlight, colorBackground;
    int recyclerViewHeight;

    private EditText etUser, etPass;
    private Button mSubmit, mRegister;
    private String user, pass;

    private ProgressDialog pDialog;

    // Clase JSONParser
    JSONParser jsonParser = new JSONParser();


    // si trabajan de manera local "localhost" :
    // En windows tienen que ir, run CMD > ipconfig
    // buscar su IP
    // y poner de la siguiente manera
    // "http://xxx.xxx.x.x:1234/cas/login.php";

    private static final String LOGIN_URL = "http://webltga.espe.edu.ec/espelapp/login.php";

    // La respuesta del JSON es
    private static final String TAG_SUCCESS = "success";
    private static final String TAG_MESSAGE = "message";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        // Setup toolbar and statusBar (really FrameLayout)
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Administración");
        statusBar = (FrameLayout) findViewById(R.id.statusBar);

        // Setup navigation drawer
        setupNavigationDrawer();


        // setup input fields
        etUser = (EditText) findViewById(R.id.username);
        etPass = (EditText) findViewById(R.id.password);

        // setup buttons
        mSubmit = (Button) findViewById(R.id.login);
        mRegister = (Button) findViewById(R.id.register);

        // register listeners
        mSubmit.setOnClickListener(this);
        mRegister.setOnClickListener(this);

    }

    public static void isNetworkAvailable(final Handler handler, final int timeout) {
        // ask fo message '0' (not connected) or '1' (connected) on 'handler'
        // the answer must be send before before within the 'timeout' (in milliseconds)

        new Thread() {
            private boolean responded = false;
            @Override
            public void run() {
                // set 'responded' to TRUE if is able to connect with google mobile (responds fast)
                new Thread() {
                    @Override
                    public void run() {
                        HttpGet requestForTest = new HttpGet("http://m.google.com");
                        try {
                            new DefaultHttpClient().execute(requestForTest); // can last...
                            responded = true;
                        }
                        catch (Exception e) {
                        }
                    }
                }.start();

                try {
                    int waited = 0;
                    while(!responded && (waited < timeout)) {
                        sleep(100);
                        if(!responded ) {
                            waited += 100;
                        }
                    }
                }
                catch(InterruptedException e) {} // do nothing
                finally {
                    if (!responded) { handler.sendEmptyMessage(0); }
                    else { handler.sendEmptyMessage(1); }
                }
            }
        }.start();
    }

    Handler h = new Handler() {
        @Override
        public void handleMessage(Message msg) {

            if (msg.what != 1) { // code if not connected

                Toast.makeText(getApplicationContext(), "Se necesita de una conexión a internet para ingresar a Administración", Toast.LENGTH_SHORT).show();

            } else { // code if connected
                new AttemptLogin().execute();
            }
        }
    };

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement

        if (id == R.id.action_search) {
            Intent i = new Intent(LoginActivity.this, BusquedaActivity.class);
            startActivity(i);
        }

        return super.onOptionsItemSelected(item);
    }

    public void setupNavigationDrawer() {

        // Setup Navigation drawer
        drawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);

        // Setup Drawer Icon
        drawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.drawer_open, R.string.drawer_close);
        drawerLayout.setDrawerListener(drawerToggle);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        drawerToggle.syncState();

        TypedValue typedValue = new TypedValue();
        LoginActivity.this.getTheme().resolveAttribute(R.attr.colorPrimaryDark, typedValue, true);
        final int color = typedValue.data;
        drawerLayout.setStatusBarBackgroundColor(color);


        // Hide Settings and Feedback buttons when navigation drawer is scrolled
        hideNavigationDrawerSettingsAndFeedbackOnScroll();

        // Setup RecyclerViews inside drawer
        setupNavigationDrawerRecyclerViews();
    }


    private void hideNavigationDrawerSettingsAndFeedbackOnScroll() {

        scrollViewNavigationDrawerContent = (ScrollView) findViewById(R.id.scrollViewNavigationDrawerContent);
        relativeLayoutScrollViewChild = (RelativeLayout) findViewById(R.id.relativeLayoutScrollViewChild);
        frameLayoutSetting1 = (FrameLayout) findViewById(R.id.frameLayoutSettings1);

        viewTreeObserverNavigationDrawerScrollView = relativeLayoutScrollViewChild.getViewTreeObserver();

        if (viewTreeObserverNavigationDrawerScrollView.isAlive()) {
            viewTreeObserverNavigationDrawerScrollView.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                @Override
                public void onGlobalLayout() {

                    if (Build.VERSION.SDK_INT > 16) {
                        relativeLayoutScrollViewChild.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                    } else {
                        relativeLayoutScrollViewChild.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                    }

                    drawerHeight = relativeLayoutScrollViewChild.getHeight();
                    scrollViewHeight = scrollViewNavigationDrawerContent.getHeight();

                    if (drawerHeight > scrollViewHeight) {
                        frameLayoutSetting1.setVisibility(View.GONE);
                    }

                    if (drawerHeight < scrollViewHeight) {
                        frameLayoutSetting1.setVisibility(View.GONE);
                    }
                }
            });
        }

        onScrollChangedListener = new ViewTreeObserver.OnScrollChangedListener() {
            @Override
            public void onScrollChanged() {

                scrollViewNavigationDrawerContent.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {

                        switch (event.getAction()) {
                            case MotionEvent.ACTION_MOVE:
                                if (scrollViewNavigationDrawerContent.getScrollY() != 0) {
                                    frameLayoutSetting1.animate().translationY(frameLayoutSetting1
                                            .getHeight()).setInterpolator(new AccelerateInterpolator(5f)).setDuration(400);
                                }
                                break;

                            case MotionEvent.ACTION_UP:
                                if (scrollViewNavigationDrawerContent.getScrollY() != 0) {
                                    frameLayoutSetting1.animate().translationY(frameLayoutSetting1
                                            .getHeight()).setInterpolator(new AccelerateInterpolator(5f)).setDuration(400);
                                }
                                break;
                        }
                        return false;
                    }
                });

                if (scrollViewNavigationDrawerContent.getScrollY() == 0) {
                    frameLayoutSetting1.animate().translationY(0)
                            .setInterpolator(new DecelerateInterpolator(5f)).setDuration(600);
                }
            }
        };

        scrollViewNavigationDrawerContent.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                ViewTreeObserver observer;
                switch (event.getAction()) {
                    case MotionEvent.ACTION_MOVE:
                        observer = scrollViewNavigationDrawerContent.getViewTreeObserver();
                        observer.addOnScrollChangedListener(onScrollChangedListener);
                        break;

                    case MotionEvent.ACTION_UP:
                        observer = scrollViewNavigationDrawerContent.getViewTreeObserver();
                        observer.addOnScrollChangedListener(onScrollChangedListener);
                        break;
                }

                return false;
            }
        });
    }

    private void setupNavigationDrawerRecyclerViews() {

        // RecyclerView 1
        recyclerViewDrawer1 = (RecyclerView) findViewById(R.id.recyclerViewDrawer1);
        linearLayoutManager = new LinearLayoutManager(LoginActivity.this);
        recyclerViewDrawer1.setLayoutManager(linearLayoutManager);

        drawerItems1 = new ArrayList<>();
        drawerItems1.add(new DrawerItem(getResources().getDrawable(R.drawable.ic_math_compass), "Ciencias Exactas"));
        drawerItems1.add(new DrawerItem(getResources().getDrawable(R.drawable.ic_beaker), "Energía y Mecánica"));
        drawerItems1.add(new DrawerItem(getResources().getDrawable(R.drawable.ic_computer), "Eléctrica y Electrónica"));
        drawerItems1.add(new DrawerItem(getResources().getDrawable(R.drawable.ic_earth), "Lenguas"));

        drawerAdapter1 = new DrawerAdapter(drawerItems1);
        recyclerViewDrawer1.setAdapter(drawerAdapter1);

        recyclerViewDrawer1.setMinimumHeight(convertDpToPx(192));
        recyclerViewDrawer1.setHasFixedSize(true);

        // RecyclerView 2
        recyclerViewDrawer2 = (RecyclerView) findViewById(R.id.recyclerViewDrawer2);
        linearLayoutManager2 = new LinearLayoutManager(LoginActivity.this);
        recyclerViewDrawer2.setLayoutManager(linearLayoutManager2);

        drawerItems2 = new ArrayList<>();
        drawerItems2.add(new DrawerItem(getResources().getDrawable(R.drawable.ic_action_content_drafts), "Mi Espe"));
        drawerItems2.add(new DrawerItem(getResources().getDrawable(R.drawable.ic_action_content_send), "Moodle Espe"));
        drawerItems2.add(new DrawerItem(getResources().getDrawable(R.drawable.ic_action_social_notifications_on), "Blog Espe"));

        drawerAdapter2 = new DrawerAdapter(drawerItems2);
        recyclerViewDrawer2.setAdapter(drawerAdapter2);

        recyclerViewDrawer2.setMinimumHeight(convertDpToPx(144));
        recyclerViewDrawer2.setHasFixedSize(true);

        // RecyclerView 3
        recyclerViewDrawer3 = (RecyclerView) findViewById(R.id.recyclerViewDrawer3);
        linearLayoutManager3 = new LinearLayoutManager(LoginActivity.this);
        recyclerViewDrawer3.setLayoutManager(linearLayoutManager3);

        drawerItems3 = new ArrayList<>();
        drawerItems3.add(new DrawerItem(getResources().getDrawable(R.drawable.ic_action_action_label), "Agenda Telefónica Espe"));
        drawerItems3.add(new DrawerItem(getResources().getDrawable(R.drawable.ic_action_action_label), "Quipux"));
        drawerItems3.add(new DrawerItem(getResources().getDrawable(R.drawable.ic_action_action_label), "Radio Eskpe"));

        drawerAdapter3 = new DrawerAdapter(drawerItems3);
        recyclerViewDrawer3.setAdapter(drawerAdapter3);

        recyclerViewDrawer3.setMinimumHeight(convertDpToPx(144));
        recyclerViewDrawer3.setHasFixedSize(true);

        // RecyclerView Settings
        recyclerViewDrawerSettings = (RecyclerView) findViewById(R.id.recyclerViewDrawerSettings);
        linearLayoutManagerSettings = new LinearLayoutManager(LoginActivity.this);
        recyclerViewDrawerSettings.setLayoutManager(linearLayoutManagerSettings);

        drawerItemsSettings = new ArrayList<>();
        drawerItemsSettings.add(new DrawerItem(getResources().getDrawable(R.drawable.ic_action_action_settings), "Administración"));
        drawerItemsSettings.add(new DrawerItem(getResources().getDrawable(R.drawable.ic_action_action_help), "Acerca De"));

        drawerAdapterSettings = new DrawerAdapter(drawerItemsSettings);
        recyclerViewDrawerSettings.setAdapter(drawerAdapterSettings);

        recyclerViewDrawerSettings.setMinimumHeight(convertDpToPx(96));
        recyclerViewDrawerSettings.setHasFixedSize(true);

        // Why have I to calc recyclerView height?
        // Because recyclerView at this moment doesn't support wrap_content, this cause an height of 0 px

        // Get colorPrimary, textColorPrimary, colorControlHighlight and background to apply to selected items
        typedValueColorPrimary = new TypedValue();
        getTheme().resolveAttribute(R.attr.colorPrimary, typedValueColorPrimary, true);
        colorPrimary = typedValueColorPrimary.data;

        typedValueTextColorPrimary = new TypedValue();
        getTheme().resolveAttribute(android.R.attr.textColorPrimary, typedValueTextColorPrimary, true);
        textColorPrimary = typedValueTextColorPrimary.data;

        typedValueTextColorControlHighlight = new TypedValue();
        getTheme().resolveAttribute(R.attr.colorControlHighlight, typedValueTextColorControlHighlight, true);
        colorControlHighlight = typedValueTextColorControlHighlight.data;

        typedValueColorBackground = new TypedValue();
        getTheme().resolveAttribute(android.R.attr.colorBackground, typedValueColorBackground, true);
        colorBackground = typedValueColorBackground.data;

        // Set icons alpha at start
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                // Do something after some time
                for (int i = 0; i < recyclerViewDrawer1.getChildCount(); i++) {
                    ImageView imageViewDrawerItemIcon = (ImageView) recyclerViewDrawer1.getChildAt(i).findViewById(R.id.imageViewDrawerItemIcon);
                    TextView textViewDrawerItemTitle = (TextView) recyclerViewDrawer1.getChildAt(i).findViewById(R.id.textViewDrawerItemTitle);
                    LinearLayout linearLayoutItem = (LinearLayout) recyclerViewDrawer1.getChildAt(i).findViewById(R.id.linearLayoutItem);
                    if (i == 0) {
                        imageViewDrawerItemIcon.setColorFilter(colorPrimary);
                        if (Build.VERSION.SDK_INT > 15) {
                            imageViewDrawerItemIcon.setImageAlpha(255);
                        } else {
                            imageViewDrawerItemIcon.setAlpha(255);
                        }
                        textViewDrawerItemTitle.setTextColor(colorPrimary);
                        linearLayoutItem.setBackgroundColor(colorControlHighlight);
                    } else {
                        imageViewDrawerItemIcon.setColorFilter(textColorPrimary);
                        if (Build.VERSION.SDK_INT > 15) {
                            imageViewDrawerItemIcon.setImageAlpha(138);
                        } else {
                            imageViewDrawerItemIcon.setAlpha(138);
                        }
                        textViewDrawerItemTitle.setTextColor(textColorPrimary);
                        linearLayoutItem.setBackgroundColor(colorBackground);
                    }
                }
                for (int i = 0; i < recyclerViewDrawer2.getChildCount(); i++) {
                    ImageView imageViewDrawerItemIcon = (ImageView) recyclerViewDrawer2.getChildAt(i).findViewById(R.id.imageViewDrawerItemIcon);
                    TextView textViewDrawerItemTitle = (TextView) recyclerViewDrawer2.getChildAt(i).findViewById(R.id.textViewDrawerItemTitle);
                    LinearLayout linearLayoutItem = (LinearLayout) recyclerViewDrawer2.getChildAt(i).findViewById(R.id.linearLayoutItem);
                    imageViewDrawerItemIcon.setColorFilter(textColorPrimary);
                    if (Build.VERSION.SDK_INT > 15) {
                        imageViewDrawerItemIcon.setImageAlpha(138);
                    } else {
                        imageViewDrawerItemIcon.setAlpha(138);
                    }
                    textViewDrawerItemTitle.setTextColor(textColorPrimary);
                    linearLayoutItem.setBackgroundColor(colorBackground);
                }
                for (int i = 0; i < recyclerViewDrawer3.getChildCount(); i++) {
                    ImageView imageViewDrawerItemIcon = (ImageView) recyclerViewDrawer3.getChildAt(i).findViewById(R.id.imageViewDrawerItemIcon);
                    TextView textViewDrawerItemTitle = (TextView) recyclerViewDrawer3.getChildAt(i).findViewById(R.id.textViewDrawerItemTitle);
                    LinearLayout linearLayoutItem = (LinearLayout) recyclerViewDrawer3.getChildAt(i).findViewById(R.id.linearLayoutItem);
                    imageViewDrawerItemIcon.setColorFilter(textColorPrimary);
                    if (Build.VERSION.SDK_INT > 15) {
                        imageViewDrawerItemIcon.setImageAlpha(67);
                    } else {
                        imageViewDrawerItemIcon.setAlpha(67);
                    }
                    textViewDrawerItemTitle.setTextColor(textColorPrimary);
                    linearLayoutItem.setBackgroundColor(colorBackground);
                }
                for (int i = 0; i < recyclerViewDrawerSettings.getChildCount(); i++) {
                    ImageView imageViewDrawerItemIcon = (ImageView) recyclerViewDrawerSettings.getChildAt(i).findViewById(R.id.imageViewDrawerItemIcon);
                    TextView textViewDrawerItemTitle = (TextView) recyclerViewDrawerSettings.getChildAt(i).findViewById(R.id.textViewDrawerItemTitle);
                    LinearLayout linearLayoutItem = (LinearLayout) recyclerViewDrawerSettings.getChildAt(i).findViewById(R.id.linearLayoutItem);
                    imageViewDrawerItemIcon.setColorFilter(textColorPrimary);
                    if (Build.VERSION.SDK_INT > 15) {
                        imageViewDrawerItemIcon.setImageAlpha(138);
                    } else {
                        imageViewDrawerItemIcon.setAlpha(138);
                    }
                    textViewDrawerItemTitle.setTextColor(textColorPrimary);
                    linearLayoutItem.setBackgroundColor(colorBackground);
                }

                ImageView imageViewSettingsIcon = (ImageView) findViewById(R.id.imageViewSettingsIcon);
                TextView textViewSettingsTitle = (TextView) findViewById(R.id.textViewSettingsTitle);
                imageViewSettingsIcon.setColorFilter(textColorPrimary);
                if (Build.VERSION.SDK_INT > 15) {
                    imageViewSettingsIcon.setImageAlpha(138);
                } else {
                    imageViewSettingsIcon.setAlpha(138);
                }
                textViewSettingsTitle.setTextColor(textColorPrimary);
                ImageView imageViewHelpIcon = (ImageView) findViewById(R.id.imageViewHelpIcon);
                TextView textViewHelpTitle = (TextView) findViewById(R.id.textViewHelpTitle);
                imageViewHelpIcon.setColorFilter(textColorPrimary);
                if (Build.VERSION.SDK_INT > 15) {
                    imageViewHelpIcon.setImageAlpha(138);
                } else {
                    imageViewHelpIcon.setAlpha(138);
                }
                textViewHelpTitle.setTextColor(textColorPrimary);
            }
        }, 250);

        itemClickSupport1 = ItemClickSupport.addTo(recyclerViewDrawer1);
        itemClickSupport1.setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
            @Override
            public void onItemClick(RecyclerView parent, View view, int position, long id) {
                for (int i = 0; i < recyclerViewDrawer1.getChildCount(); i++) {
                    ImageView imageViewDrawerItemIcon = (ImageView) recyclerViewDrawer1.getChildAt(i).findViewById(R.id.imageViewDrawerItemIcon);
                    TextView textViewDrawerItemTitle = (TextView) recyclerViewDrawer1.getChildAt(i).findViewById(R.id.textViewDrawerItemTitle);
                    LinearLayout linearLayoutItem = (LinearLayout) recyclerViewDrawer1.getChildAt(i).findViewById(R.id.linearLayoutItem);
                    if (i == position) {
                        imageViewDrawerItemIcon.setColorFilter(colorPrimary);
                        if (Build.VERSION.SDK_INT > 15) {
                            imageViewDrawerItemIcon.setImageAlpha(255);
                        } else {
                            imageViewDrawerItemIcon.setAlpha(255);
                        }
                        textViewDrawerItemTitle.setTextColor(colorPrimary);
                        linearLayoutItem.setBackgroundColor(colorControlHighlight);
                    } else {
                        imageViewDrawerItemIcon.setColorFilter(textColorPrimary);
                        if (Build.VERSION.SDK_INT > 15) {
                            imageViewDrawerItemIcon.setImageAlpha(138);
                        } else {
                            imageViewDrawerItemIcon.setAlpha(138);
                        }
                        textViewDrawerItemTitle.setTextColor(textColorPrimary);
                        linearLayoutItem.setBackgroundColor(colorBackground);
                    }
                }
                for (int i = 0; i < recyclerViewDrawer2.getChildCount(); i++) {
                    ImageView imageViewDrawerItemIcon = (ImageView) recyclerViewDrawer2.getChildAt(i).findViewById(R.id.imageViewDrawerItemIcon);
                    TextView textViewDrawerItemTitle = (TextView) recyclerViewDrawer2.getChildAt(i).findViewById(R.id.textViewDrawerItemTitle);
                    LinearLayout linearLayoutItem = (LinearLayout) recyclerViewDrawer2.getChildAt(i).findViewById(R.id.linearLayoutItem);
                    imageViewDrawerItemIcon.setColorFilter(textColorPrimary);
                    if (Build.VERSION.SDK_INT > 15) {
                        imageViewDrawerItemIcon.setImageAlpha(138);
                    } else {
                        imageViewDrawerItemIcon.setAlpha(138);
                    }
                    textViewDrawerItemTitle.setTextColor(textColorPrimary);
                    linearLayoutItem.setBackgroundColor(colorBackground);
                }
                for (int i = 0; i < recyclerViewDrawer3.getChildCount(); i++) {
                    ImageView imageViewDrawerItemIcon = (ImageView) recyclerViewDrawer3.getChildAt(i).findViewById(R.id.imageViewDrawerItemIcon);
                    TextView textViewDrawerItemTitle = (TextView) recyclerViewDrawer3.getChildAt(i).findViewById(R.id.textViewDrawerItemTitle);
                    LinearLayout linearLayoutItem = (LinearLayout) recyclerViewDrawer3.getChildAt(i).findViewById(R.id.linearLayoutItem);
                    imageViewDrawerItemIcon.setColorFilter(textColorPrimary);
                    textViewDrawerItemTitle.setTextColor(textColorPrimary);
                    if (Build.VERSION.SDK_INT > 15) {
                        imageViewDrawerItemIcon.setImageAlpha(67);
                    } else {
                        imageViewDrawerItemIcon.setAlpha(67);
                    }
                    linearLayoutItem.setBackgroundColor(colorBackground);
                }
                for (int i = 0; i < recyclerViewDrawerSettings.getChildCount(); i++) {
                    ImageView imageViewDrawerItemIcon = (ImageView) recyclerViewDrawerSettings.getChildAt(i).findViewById(R.id.imageViewDrawerItemIcon);
                    TextView textViewDrawerItemTitle = (TextView) recyclerViewDrawerSettings.getChildAt(i).findViewById(R.id.textViewDrawerItemTitle);
                    LinearLayout linearLayoutItem = (LinearLayout) recyclerViewDrawerSettings.getChildAt(i).findViewById(R.id.linearLayoutItem);
                    imageViewDrawerItemIcon.setColorFilter(textColorPrimary);
                    if (Build.VERSION.SDK_INT > 15) {
                        imageViewDrawerItemIcon.setImageAlpha(138);
                    } else {
                        imageViewDrawerItemIcon.setAlpha(138);
                    }
                    textViewDrawerItemTitle.setTextColor(textColorPrimary);
                    linearLayoutItem.setBackgroundColor(colorBackground);
                }
                Intent i = new Intent(LoginActivity.this, DepartamentoInfoActivity.class);
                switch (position) {
                    case 0:
                        /*Toast.makeText(getApplicationContext(), "Posicion " + position,
                                Toast.LENGTH_SHORT).show();*/
                        i.putExtra("departamento", "1");
                        startActivity(i);
                        //MenuDrawerActivity.this.startActivity(new Intent(MenuDrawerActivity.this, LocationActivity.class));
                        //MenuDrawerActivity.this.startActivity(new Intent(MenuDrawerActivity.this, CienciasExactasActivity.class));
                        break;
                    case 1:
                        i.putExtra("departamento", "2");
                        startActivity(i);
                        /*MenuDrawerActivity.this.startActivity(new Intent(MenuDrawerActivity.this, MenuActivity.class));
                        Toast.makeText(getApplicationContext(), "Posicion " + position,
                                Toast.LENGTH_SHORT).show();*/
                        break;
                    case 2:
                        i.putExtra("departamento", "3");
                        startActivity(i);
                        break;
                    case 3:
                        i.putExtra("departamento", "4");
                        startActivity(i);
                        break;
                }
            }
        });

        itemClickSupport2 = ItemClickSupport.addTo(recyclerViewDrawer2);
        itemClickSupport2.setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
            @Override
            public void onItemClick(RecyclerView parent, View view, int position, long id) {
                for (int i = 0; i < recyclerViewDrawer2.getChildCount(); i++) {
                    ImageView imageViewDrawerItemIcon = (ImageView) recyclerViewDrawer2.getChildAt(i).findViewById(R.id.imageViewDrawerItemIcon);
                    TextView textViewDrawerItemTitle = (TextView) recyclerViewDrawer2.getChildAt(i).findViewById(R.id.textViewDrawerItemTitle);
                    LinearLayout linearLayoutItem = (LinearLayout) recyclerViewDrawer2.getChildAt(i).findViewById(R.id.linearLayoutItem);
                    if (i == position) {
                        imageViewDrawerItemIcon.setColorFilter(colorPrimary);
                        if (Build.VERSION.SDK_INT > 15) {
                            imageViewDrawerItemIcon.setImageAlpha(255);
                        } else {
                            imageViewDrawerItemIcon.setAlpha(255);
                        }
                        textViewDrawerItemTitle.setTextColor(colorPrimary);
                        linearLayoutItem.setBackgroundColor(colorControlHighlight);
                    } else {
                        imageViewDrawerItemIcon.setColorFilter(textColorPrimary);
                        if (Build.VERSION.SDK_INT > 15) {
                            imageViewDrawerItemIcon.setImageAlpha(138);
                        } else {
                            imageViewDrawerItemIcon.setAlpha(138);
                        }
                        textViewDrawerItemTitle.setTextColor(textColorPrimary);
                        linearLayoutItem.setBackgroundColor(colorBackground);
                    }
                }
                for (int i = 0; i < recyclerViewDrawer1.getChildCount(); i++) {
                    ImageView imageViewDrawerItemIcon = (ImageView) recyclerViewDrawer1.getChildAt(i).findViewById(R.id.imageViewDrawerItemIcon);
                    TextView textViewDrawerItemTitle = (TextView) recyclerViewDrawer1.getChildAt(i).findViewById(R.id.textViewDrawerItemTitle);
                    LinearLayout linearLayoutItem = (LinearLayout) recyclerViewDrawer1.getChildAt(i).findViewById(R.id.linearLayoutItem);
                    imageViewDrawerItemIcon.setColorFilter(textColorPrimary);
                    if (Build.VERSION.SDK_INT > 15) {
                        imageViewDrawerItemIcon.setImageAlpha(138);
                    } else {
                        imageViewDrawerItemIcon.setAlpha(138);
                    }
                    textViewDrawerItemTitle.setTextColor(textColorPrimary);
                    linearLayoutItem.setBackgroundColor(colorBackground);
                }
                for (int i = 0; i < recyclerViewDrawer3.getChildCount(); i++) {
                    ImageView imageViewDrawerItemIcon = (ImageView) recyclerViewDrawer3.getChildAt(i).findViewById(R.id.imageViewDrawerItemIcon);
                    TextView textViewDrawerItemTitle = (TextView) recyclerViewDrawer3.getChildAt(i).findViewById(R.id.textViewDrawerItemTitle);
                    LinearLayout linearLayoutItem = (LinearLayout) recyclerViewDrawer3.getChildAt(i).findViewById(R.id.linearLayoutItem);
                    imageViewDrawerItemIcon.setColorFilter(textColorPrimary);
                    if (Build.VERSION.SDK_INT > 15) {
                        imageViewDrawerItemIcon.setImageAlpha(67);
                    } else {
                        imageViewDrawerItemIcon.setAlpha(67);
                    }
                    textViewDrawerItemTitle.setTextColor(textColorPrimary);
                    linearLayoutItem.setBackgroundColor(colorBackground);
                }
                for (int i = 0; i < recyclerViewDrawerSettings.getChildCount(); i++) {
                    ImageView imageViewDrawerItemIcon = (ImageView) recyclerViewDrawerSettings.getChildAt(i).findViewById(R.id.imageViewDrawerItemIcon);
                    TextView textViewDrawerItemTitle = (TextView) recyclerViewDrawerSettings.getChildAt(i).findViewById(R.id.textViewDrawerItemTitle);
                    LinearLayout linearLayoutItem = (LinearLayout) recyclerViewDrawerSettings.getChildAt(i).findViewById(R.id.linearLayoutItem);
                    imageViewDrawerItemIcon.setColorFilter(textColorPrimary);
                    if (Build.VERSION.SDK_INT > 15) {
                        imageViewDrawerItemIcon.setImageAlpha(138);
                    } else {
                        imageViewDrawerItemIcon.setAlpha(138);
                    }
                    textViewDrawerItemTitle.setTextColor(textColorPrimary);
                    linearLayoutItem.setBackgroundColor(colorBackground);
                }
                Intent i = new Intent(LoginActivity.this, SitiosActivity.class);
                switch (position){
                    case 0:
                        i.putExtra("sitio", "1");
                        startActivity(i);
                        //MenuDrawerActivity.this.startActivity(new Intent(MenuDrawerActivity.this, MenuActivity.class));
                        break;
                    case 1:
                        i.putExtra("sitio", "2");
                        startActivity(i);
                        //Toast.makeText(getApplicationContext(), "Posicion " + position, Toast.LENGTH_SHORT).show();
                        break;
                    case 2:
                        i.putExtra("sitio", "3");
                        startActivity(i);
                        break;
                }
            }
        });
        itemClickSupport3 = ItemClickSupport.addTo(recyclerViewDrawer3);
        itemClickSupport3.setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
            @Override
            public void onItemClick(RecyclerView parent, View view, int position, long id) {
                for (int i = 0; i < recyclerViewDrawer3.getChildCount(); i++) {
                    ImageView imageViewDrawerItemIcon = (ImageView) recyclerViewDrawer3.getChildAt(i).findViewById(R.id.imageViewDrawerItemIcon);
                    TextView textViewDrawerItemTitle = (TextView) recyclerViewDrawer3.getChildAt(i).findViewById(R.id.textViewDrawerItemTitle);
                    LinearLayout linearLayoutItem = (LinearLayout) recyclerViewDrawer3.getChildAt(i).findViewById(R.id.linearLayoutItem);
                    if (i == position) {
                        imageViewDrawerItemIcon.setColorFilter(colorPrimary);
                        if (Build.VERSION.SDK_INT > 15) {
                            imageViewDrawerItemIcon.setImageAlpha(138);
                        } else {
                            imageViewDrawerItemIcon.setAlpha(138);
                        }
                        textViewDrawerItemTitle.setTextColor(colorPrimary);
                        linearLayoutItem.setBackgroundColor(colorControlHighlight);
                    } else {
                        imageViewDrawerItemIcon.setColorFilter(textColorPrimary);
                        if (Build.VERSION.SDK_INT > 15) {
                            imageViewDrawerItemIcon.setImageAlpha(67);
                        } else {
                            imageViewDrawerItemIcon.setAlpha(67);
                        }
                        textViewDrawerItemTitle.setTextColor(textColorPrimary);
                        linearLayoutItem.setBackgroundColor(colorBackground);
                    }
                }
                for (int i = 0; i < recyclerViewDrawer1.getChildCount(); i++) {
                    ImageView imageViewDrawerItemIcon = (ImageView) recyclerViewDrawer1.getChildAt(i).findViewById(R.id.imageViewDrawerItemIcon);
                    TextView textViewDrawerItemTitle = (TextView) recyclerViewDrawer1.getChildAt(i).findViewById(R.id.textViewDrawerItemTitle);
                    LinearLayout linearLayoutItem = (LinearLayout) recyclerViewDrawer1.getChildAt(i).findViewById(R.id.linearLayoutItem);
                    imageViewDrawerItemIcon.setColorFilter(textColorPrimary);
                    if (Build.VERSION.SDK_INT > 15) {
                        imageViewDrawerItemIcon.setImageAlpha(138);
                    } else {
                        imageViewDrawerItemIcon.setAlpha(138);
                    }
                    textViewDrawerItemTitle.setTextColor(textColorPrimary);
                    linearLayoutItem.setBackgroundColor(colorBackground);
                }
                for (int i = 0; i < recyclerViewDrawer2.getChildCount(); i++) {
                    ImageView imageViewDrawerItemIcon = (ImageView) recyclerViewDrawer2.getChildAt(i).findViewById(R.id.imageViewDrawerItemIcon);
                    TextView textViewDrawerItemTitle = (TextView) recyclerViewDrawer2.getChildAt(i).findViewById(R.id.textViewDrawerItemTitle);
                    LinearLayout linearLayoutItem = (LinearLayout) recyclerViewDrawer2.getChildAt(i).findViewById(R.id.linearLayoutItem);
                    imageViewDrawerItemIcon.setColorFilter(textColorPrimary);
                    if (Build.VERSION.SDK_INT > 15) {
                        imageViewDrawerItemIcon.setImageAlpha(138);
                    } else {
                        imageViewDrawerItemIcon.setAlpha(138);
                    }
                    textViewDrawerItemTitle.setTextColor(textColorPrimary);
                    linearLayoutItem.setBackgroundColor(colorBackground);
                }
                for (int i = 0; i < recyclerViewDrawerSettings.getChildCount(); i++) {
                    ImageView imageViewDrawerItemIcon = (ImageView) recyclerViewDrawerSettings.getChildAt(i).findViewById(R.id.imageViewDrawerItemIcon);
                    TextView textViewDrawerItemTitle = (TextView) recyclerViewDrawerSettings.getChildAt(i).findViewById(R.id.textViewDrawerItemTitle);
                    LinearLayout linearLayoutItem = (LinearLayout) recyclerViewDrawerSettings.getChildAt(i).findViewById(R.id.linearLayoutItem);
                    imageViewDrawerItemIcon.setColorFilter(textColorPrimary);
                    if (Build.VERSION.SDK_INT > 15) {
                        imageViewDrawerItemIcon.setImageAlpha(138);
                    } else {
                        imageViewDrawerItemIcon.setAlpha(138);
                    }
                    textViewDrawerItemTitle.setTextColor(textColorPrimary);
                    linearLayoutItem.setBackgroundColor(colorBackground);
                }
                Intent i = new Intent(LoginActivity.this, SitiosActivity.class);
                switch (position){
                    case 0:
                        i.putExtra("sitio", "4");
                        startActivity(i);
                        //MenuDrawerActivity.this.startActivity(new Intent(MenuDrawerActivity.this, MenuActivity.class));
                        break;
                    case 1:
                        i.putExtra("sitio", "5");
                        startActivity(i);
                        //Toast.makeText(getApplicationContext(), "Posicion " + position, Toast.LENGTH_SHORT).show();
                        break;
                    case 2:
                        i.putExtra("sitio", "6");
                        startActivity(i);
                        break;
                }
            }
        });

        //Clicks para settings

        itemClickSupportSettings = ItemClickSupport.addTo(recyclerViewDrawerSettings);
        itemClickSupportSettings.setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
            @Override
            public void onItemClick(RecyclerView parent, View view, int position, long id) {
                for (int i = 0; i < recyclerViewDrawerSettings.getChildCount(); i++) {
                    ImageView imageViewDrawerItemIcon = (ImageView) recyclerViewDrawerSettings.getChildAt(i).findViewById(R.id.imageViewDrawerItemIcon);
                    TextView textViewDrawerItemTitle = (TextView) recyclerViewDrawerSettings.getChildAt(i).findViewById(R.id.textViewDrawerItemTitle);
                    LinearLayout linearLayoutItem = (LinearLayout) recyclerViewDrawerSettings.getChildAt(i).findViewById(R.id.linearLayoutItem);
                    if (i == position) {
                        imageViewDrawerItemIcon.setColorFilter(colorPrimary);
                        if (Build.VERSION.SDK_INT > 15) {
                            imageViewDrawerItemIcon.setImageAlpha(138);
                        } else {
                            imageViewDrawerItemIcon.setAlpha(138);
                        }
                        textViewDrawerItemTitle.setTextColor(colorPrimary);
                        linearLayoutItem.setBackgroundColor(colorControlHighlight);
                    } else {
                        imageViewDrawerItemIcon.setColorFilter(textColorPrimary);
                        if (Build.VERSION.SDK_INT > 15) {
                            imageViewDrawerItemIcon.setImageAlpha(67);
                        } else {
                            imageViewDrawerItemIcon.setAlpha(67);
                        }
                        textViewDrawerItemTitle.setTextColor(textColorPrimary);
                        linearLayoutItem.setBackgroundColor(colorBackground);
                    }
                }
                for (int i = 0; i < recyclerViewDrawer1.getChildCount(); i++) {
                    ImageView imageViewDrawerItemIcon = (ImageView) recyclerViewDrawer1.getChildAt(i).findViewById(R.id.imageViewDrawerItemIcon);
                    TextView textViewDrawerItemTitle = (TextView) recyclerViewDrawer1.getChildAt(i).findViewById(R.id.textViewDrawerItemTitle);
                    LinearLayout linearLayoutItem = (LinearLayout) recyclerViewDrawer1.getChildAt(i).findViewById(R.id.linearLayoutItem);
                    imageViewDrawerItemIcon.setColorFilter(textColorPrimary);
                    if (Build.VERSION.SDK_INT > 15) {
                        imageViewDrawerItemIcon.setImageAlpha(138);
                    } else {
                        imageViewDrawerItemIcon.setAlpha(138);
                    }
                    textViewDrawerItemTitle.setTextColor(textColorPrimary);
                    linearLayoutItem.setBackgroundColor(colorBackground);
                }
                for (int i = 0; i < recyclerViewDrawer2.getChildCount(); i++) {
                    ImageView imageViewDrawerItemIcon = (ImageView) recyclerViewDrawer2.getChildAt(i).findViewById(R.id.imageViewDrawerItemIcon);
                    TextView textViewDrawerItemTitle = (TextView) recyclerViewDrawer2.getChildAt(i).findViewById(R.id.textViewDrawerItemTitle);
                    LinearLayout linearLayoutItem = (LinearLayout) recyclerViewDrawer2.getChildAt(i).findViewById(R.id.linearLayoutItem);
                    imageViewDrawerItemIcon.setColorFilter(textColorPrimary);
                    if (Build.VERSION.SDK_INT > 15) {
                        imageViewDrawerItemIcon.setImageAlpha(138);
                    } else {
                        imageViewDrawerItemIcon.setAlpha(138);
                    }
                    textViewDrawerItemTitle.setTextColor(textColorPrimary);
                    linearLayoutItem.setBackgroundColor(colorBackground);
                }

                for (int i = 0; i < recyclerViewDrawer3.getChildCount(); i++) {
                    ImageView imageViewDrawerItemIcon = (ImageView) recyclerViewDrawer3.getChildAt(i).findViewById(R.id.imageViewDrawerItemIcon);
                    TextView textViewDrawerItemTitle = (TextView) recyclerViewDrawer3.getChildAt(i).findViewById(R.id.textViewDrawerItemTitle);
                    LinearLayout linearLayoutItem = (LinearLayout) recyclerViewDrawer3.getChildAt(i).findViewById(R.id.linearLayoutItem);
                    imageViewDrawerItemIcon.setColorFilter(textColorPrimary);
                    if (Build.VERSION.SDK_INT > 15) {
                        imageViewDrawerItemIcon.setImageAlpha(138);
                    } else {
                        imageViewDrawerItemIcon.setAlpha(138);
                    }
                    textViewDrawerItemTitle.setTextColor(textColorPrimary);
                    linearLayoutItem.setBackgroundColor(colorBackground);
                }

                switch (position){
                    case 0:
                        LoginActivity.this.startActivity(new Intent(LoginActivity.this, LoginActivity.class));
                        break;
                    case 1:
                        LoginActivity.this.startActivity(new Intent(LoginActivity.this, AcercaDeActivity.class));
                        break;

                }
            }
        });
    }

    public int convertDpToPx(int dp) {
        // Get the screen's density scale
        final float scale = getResources().getDisplayMetrics().density;
        // Convert the dps to pixels, based on density scale
        return (int) (dp * scale + 0.5f);
    }


    @Override
    protected void onStop() {
        drawerLayout.closeDrawers();
        super.onStop();
    }

    //Login

    class AttemptLogin extends AsyncTask<String, String, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(LoginActivity.this);
            pDialog.setMessage("Ingresando a Administración");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            pDialog.show();
        }

        @Override
        protected String doInBackground(String... args) {
            int success;
            String username = user;
            String password = pass;
            try {
                // Building Parameters
                List params = new ArrayList();
                params.add(new BasicNameValuePair("username", username));
                params.add(new BasicNameValuePair("password", password));

                Log.d("request!", "starting");
                // getting product details by making HTTP request
                JSONObject json = jsonParser.makeHttpRequest(LOGIN_URL, "POST",
                        params);

                // check your log for json response
                Log.d("Login attempt", json.toString());

                // json success tag
                success = json.getInt(TAG_SUCCESS);
                if (success == 1) {
                    Log.d("Login Successful!", json.toString());
                    // save etUser data
                    SharedPreferences sp = PreferenceManager
                            .getDefaultSharedPreferences(LoginActivity.this);
                    Editor edit = sp.edit();
                    edit.putString("username", username);
                    edit.commit();

                    Intent i = new Intent(LoginActivity.this, DepartamentosActivity.class);
                    finish();
                    startActivity(i);
                    return json.getString(TAG_MESSAGE);
                } else {
                    Log.d("Login Failure!", json.getString(TAG_MESSAGE));
                    return json.getString(TAG_MESSAGE);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

            return null;

        }

        protected void onPostExecute(String file_url) {
            // dismiss the dialog once product deleted
            pDialog.dismiss();
            if (file_url != null) {
                Toast.makeText(LoginActivity.this, file_url, Toast.LENGTH_LONG).show();
            }
        }
    }


    @Override
    public void onClick(View v) {
        // TODO Auto-generated method stub
        switch (v.getId()) {
            case R.id.login:
                if(etUser.getText().toString().equals("") || etPass.getText().toString().equals("")){
                    Toast.makeText(this, "Ingrese el usuario y la contraseña", Toast.LENGTH_LONG).show();
                }else{
                    user = etUser.getText().toString();
                    pass = etPass.getText().toString();
                    isNetworkAvailable(h, 2000);
                }
                break;
            case R.id.register:
                Intent i = new Intent(this, Register.class);
                startActivity(i);
                break;


        }
    }
}