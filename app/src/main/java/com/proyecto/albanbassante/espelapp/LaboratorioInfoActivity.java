package com.proyecto.albanbassante.espelapp;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Handler;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.webkit.WebView;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.joooonho.SelectableRoundedImageView;
import com.proyecto.albanbassante.espelapp.RecyclerView.Adapters.DrawerAdapter;
import com.proyecto.albanbassante.espelapp.RecyclerView.Classes.DrawerItem;
import com.proyecto.albanbassante.espelapp.RecyclerView.Utils.ItemClickSupport;

import java.util.ArrayList;


public class LaboratorioInfoActivity extends ActionBarActivity implements View.OnClickListener{

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

    private String labCodigo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_laboratorio_info);

        Bundle extras = getIntent().getExtras();
        labCodigo = extras.getString("laboratorio");

        WebView view = (WebView) findViewById(R.id.textContent);
        SelectableRoundedImageView srivLab = (SelectableRoundedImageView) findViewById(R.id.srivLab);
        String text;

        switch (labCodigo) {
            case "labCiencias1":
                srivLab.setImageResource(R.drawable.lab_modelos_simulacion);
                text = "<html><body><h3 align=\"justify\">Laboratorio De Modelos Y Simulación</h3><p align=\"justify\">";
                text+= "Con la finalidad de disponer en las instalaciones de la Universidad de las Fuerzas Armadas ESPE extensión Latacunga de un laboratorio que permita desarrollar investigación y procesos de enseñanza en el campo de la modelación y la simulación, el Departamento de Ciencias Exactas adquirió el software MATLAB, el mismo que cuenta con licencias para desarrollo de clases y de investigación en diversas áreas en las que la Universidad se encuentra inmersa.";
                text+= "</p></body></html>";
                view.loadDataWithBaseURL(null, text, "text/html", "utf-8", null);
                view.setBackgroundColor(Color.TRANSPARENT);
                break;
            case "labCiencias2":
                srivLab.setImageResource(R.drawable.lab_fisica);
                text = "<html><body><h3 align=\"justify\">Laboratorio De Física</h3><p align=\"justify\">";
                text+= "El departamento de Ciencias Exactas a partir del año 2005 por gestión del Ing. Augusto Bourgeat se inicia el Laboratorio de Física, el mismo que ha dado servicio y ha solucionado las actividades de prácticas en  las asignaturas de Física I y Física II. La infraestructura del laboratorio de Física actualmente es adecuada, pertinente y óptima. En virtud de su objeto de estudio, el Laboratorio de Física da a la enseñanza de la Física Experimental una importancia relevante, por lo que mantiene un conjunto de equipos de laboratorios dedicados primordialmente a la docencia, en los que se desarrollan, como parte de los programas curriculares, acciones específicas para la enseñanza del trabajo experimental. El Laboratorio de Física dispone de 4 mesas de trabajo para realizar prácticas experimentales en: Cinemática, Dinámica, Energía Renovables, Termodinámica.";
                text+= "</p></body></html>";
                view.loadDataWithBaseURL(null, text, "text/html", "utf-8", null);
                view.setBackgroundColor(Color.TRANSPARENT);
                break;
            case "labCiencias3":
                srivLab.setImageResource(R.drawable.lab_quimica);
                text = "<html><body><h3 align=\"justify\">Laboratorio De Química</h3><p align=\"justify\">";
                text+= "El laboratorio de Química posee una Área de 105,79 metros cuadrados, está ubicado en el Bloque 4 de la Extensión y cuenta con tres mesas de trabajo, cada una de las cuales se ha dividido para que dos grupos trabajen en cada una, de tal forma que en cada práctica trabajan seis grupos en el laboratorio. Cada grupo dispone de equipos para la experimentación en el área de Química Básica, Química Inorgánica y Química Orgánica. Este Laboratorio  es la base principal para introducir a los estudiantes de las Carreras de Automotriz, Electromecánica, Mecatrónica, y Petroquímica en el conocimiento práctico de Química Básica.";
                text+= "</p></body></html>";
                view.loadDataWithBaseURL(null, text, "text/html", "utf-8", null);
                view.setBackgroundColor(Color.TRANSPARENT);
                break;


            case "labEnergia1":
                srivLab.setImageResource(R.drawable.lab_industrial);
                text = "<html><body><h3 align=\"justify\">Laboratorio Industrial</h3><p align=\"justify\">";
                text+= "Permite desarrollar las prácticas en las diferentes máquinas herramientas aplicadas en la industria (tornos, fresadoras, limadoras, rectificadoras) mediante el empleo de normas de calidad y seguridad, para cumplir con las competencias profesionales que se forman en las carreras del Departamento de Energía y Mecánica.";
                text+= "</p></body></html>";
                view.loadDataWithBaseURL(null, text, "text/html", "utf-8", null);
                view.setBackgroundColor(Color.TRANSPARENT);
                break;
            case "labEnergia2":
                srivLab.setImageResource(R.drawable.lab_autotronica);
                text = "<html><body><h3 align=\"justify\">Laboratorio De Autotrónica</h3><p align=\"justify\">";
                text+= "Tiene como objetivo capacitar a los estudiantes en los sistemas eléctricos y electrónicos de punta del automóvil, para lo que dispone de una amplia gama de herramientas e instrumental de diagnóstico, scanner multimarca, osciloscopios automotrices, herramientas sofisticadas y  vehículos con tecnología de punta (híbridos y con asistencia electrónica) a fin de lograr la formación acorde a los avances tecnológicos a fin de prestar servicios de capacitación, consultoría.";
                text+= "</p></body></html>";
                view.loadDataWithBaseURL(null, text, "text/html", "utf-8", null);
                view.setBackgroundColor(Color.TRANSPARENT);
                break;
            case "labEnergia3":
                srivLab.setImageResource(R.drawable.lab_mecanica_patio);
                text = "<html><body><h3 align=\"justify\">Laboratorio Mecánica De Patio</h3><p align=\"justify\">";
                text+= "El laboratorio dispone de equipos y maquinaria de tecnología de punta, alineadora robotizada de alineación, balanceo electrónico y enllantaje, equipos de diagnóstico. Encamina sus esfuerzos a la preparación de los estudiantes en el análisis y desarrollo de prácticas en lo referente a sistemas mecánicos automotrices y con asistencia electrónica en: frenos, suspensión, dirección, transmisión entre otros.";
                text+= "</p></body></html>";
                view.loadDataWithBaseURL(null, text, "text/html", "utf-8", null);
                view.setBackgroundColor(Color.TRANSPARENT);
                break;
            case "labEnergia4":
                srivLab.setImageResource(R.drawable.lab_motores_combustion_rectificacion);
                text = "<html><body><h3 align=\"justify\">Laboratorio De Motores Combustión Y Rectificación</h3><p align=\"justify\">";
                text+= "Se desarrollan las prácticas de desempeño térmico y mecánico  de los motores de combustión interna basados en normas y especificaciones así como ensayos con diferentes tipos de combustibles fósiles y no fósiles, dispone de un sistema completo de maquinaria para la reparación y rectificación de motores que se encuentra a disposición para el desarrollo de investigaciones técnicas y tecnológicas.";
                text+= "</p></body></html>";
                view.loadDataWithBaseURL(null, text, "text/html", "utf-8", null);
                view.setBackgroundColor(Color.TRANSPARENT);
                break;
            case "labEnergia5":
                srivLab.setImageResource(R.drawable.lab_refrigeracion_aire);
                text = "<html><body><h3 align=\"justify\">Laboratorio De Refrigeración Y Aire Acondicionado</h3><p align=\"justify\">";
                text+= "El laboratorio dispone de equipos y maquinaria de tecnología de punta, alineadora robotizada de alineación, balanceo electrónico y enllantaje, equipos de diagnóstico. Encamina sus esfuerzos a la preparación de los estudiantes en el análisis y desarrollo de prácticas en lo referente a sistemas mecánicos automotrices y con asistencia electrónica en: frenos, suspensión, dirección, transmisión entre otros.";
                text+= "</p></body></html>";
                view.loadDataWithBaseURL(null, text, "text/html", "utf-8", null);
                view.setBackgroundColor(Color.TRANSPARENT);
                break;
            case "labEnergia6":
                srivLab.setImageResource(R.drawable.lab_robotica_industrial);
                text = "<html><body><h3 align=\"justify\">Laboratorio De Robótica Industrial</h3><p align=\"justify\">";
                text+= "El Laboratorio cuenta con maquinaria y equipos de tecnología de punta, tales como brazos robóticos, bandas transportadoras con Sistemas HMI, procesadores de imagen, sistemas de soldadura robotizada, mesa posicionadora de soldadura; que permite a los usuarios obtener conocimiento sobre tecnologías actuales de procesos que se presentan en las industrias, relacionándolos en cada una de las distintas áreas como son mecánica, electrónica, control y desarrollo de software.";
                text+= "</p></body></html>";
                view.loadDataWithBaseURL(null, text, "text/html", "utf-8", null);
                view.setBackgroundColor(Color.TRANSPARENT);
                break;
            case "labEnergia7":
                srivLab.setImageResource(R.drawable.lab_ciencia_resistencia_materiales);
                text = "<html><body><h3 align=\"justify\">Laboratorio De Ciencia Y Resistencia De Materiales</h3><p align=\"justify\">";
                text+= "Dispone de la infraestructura tecnológica necesaria para el análisis y constitución de los diferentes materiales usados en la industria, así como el banco de pruebas universales para el desarrollo de ensayos destructivos, tracción, compresión, torsión y corte, mediante procesos computarizados que garantizan la fiabilidad de los resultados.";
                text+= "</p></body></html>";
                view.loadDataWithBaseURL(null, text, "text/html", "utf-8", null);
                view.setBackgroundColor(Color.TRANSPARENT);
                break;
            case "labEnergia8":
                srivLab.setImageResource(R.drawable.lab_control_cnc);
                text = "<html><body><h3 align=\"justify\">Laboratorio De Control Computarizado CNC</h3><p align=\"justify\">";
                text+= "Dispone del más moderno centro de mecanizado computarizado del país, diseño y la manufactura asistida por computador CAD/CAM/CAE, utilizando software y maquinaria de última generación como: Centros de Fresado y Torneado, Cortadora por Plasma, Prototipadora 3D, Scanner 3D y software especializado, entre otras contribuyendo con tecnología de punta a la sociedad y al país.";
                text+= "</p></body></html>";
                view.loadDataWithBaseURL(null, text, "text/html", "utf-8", null);
                view.setBackgroundColor(Color.TRANSPARENT);
                break;
            case "labEnergia9":
                srivLab.setImageResource(R.drawable.lab_prototipos);
                text = "<html><body><h3 align=\"justify\">Laboratorio De Prototipos</h3><p align=\"justify\">";
                text+= "El laboratorio de Prototipos fue creado con la intensión de ocupar en la construcción de vehículos prototipos o mecanismos que permitan mejorar los actuales, se apoya de otros laboratorios como CNC, Industrial, Materiales, etc. Fue creado en Enero 2011. En este laboratorio se han desarrollado los vehículos que compitieron en la fórmula Student Germany 2011, 2012. Además se realizó el rediseño del 2011 para que compita próximamente en el Germany 2014. Además se realizó partes de un Tractor agrícola monoplaza a diesel.";
                text+= "</p></body></html>";
                view.loadDataWithBaseURL(null, text, "text/html", "utf-8", null);
                view.setBackgroundColor(Color.TRANSPARENT);
                break;
            case "labEnergia10":
                srivLab.setImageResource(R.drawable.lab_soldadura);
                text = "<html><body><h3 align=\"justify\">Laboratorio De Soldadura</h3><p align=\"justify\">";
                text+= "Dispone de maquinaria y equipo especializada en procesos de soldadura por arco eléctrico con electrodo revestido (SMAW), soldadura por arco eléctrico con electrodo continuo y  protección gaseosa (GMAW), soldadura con llama oxiacetilénica (OAW), soldadura con electrodo de tungsteno y protección gaseosa (GTAW) y Corte por Plasma, efectuando trabajos de consultoría y/o investigación.";
                text+= "</p></body></html>";
                view.loadDataWithBaseURL(null, text, "text/html", "utf-8", null);
                view.setBackgroundColor(Color.TRANSPARENT);
                break;


            case "labElectrica1":
                srivLab.setImageResource(R.drawable.lab_ingenieria_software);
                text = "<html><body><h3 align=\"justify\">Laboratorio De Ingeniería De Software</h3><p align=\"justify\">";
                text+= "El laboratorio cuenta con el hardware y el software necesario para la enseñanza del proceso de ingeniería de software y permite desarrollar las prácticas en las diferentes máquinas y herramientas aplicadas a la materia en cuestión";
                text+= "</p></body></html>";
                view.loadDataWithBaseURL(null, text, "text/html", "utf-8", null);
                view.setBackgroundColor(Color.TRANSPARENT);
                break;
            case "labElectrica2":
                srivLab.setImageResource(R.drawable.lab_inteligenica_artificial);
                text = "<html><body><h3 align=\"justify\">Laboratorio De Inteligencia Artificial</h3><p align=\"justify\">";
                text+= "El laboratorio es un espacio interdisciplinario donde se combinan distintos aspectos de la Neurociencia Computacional, como son nociones de complejidad y azar en humanos, lingüística computacional, código fuente y lenguaje hablado, sistemas interactivos de diálogo (IVR) y reconocimiento del habla, análisis en tiempo real de señales cerebrales.";
                text+= "</p></body></html>";
                view.loadDataWithBaseURL(null, text, "text/html", "utf-8", null);
                view.setBackgroundColor(Color.TRANSPARENT);
                break;
            case "labElectrica3":
                srivLab.setImageResource(R.drawable.lab_circuitos_electronicos);
                text = "<html><body><h3 align=\"justify\">Laboratorio De Circuitos Electrónicos</h3><p align=\"justify\">";
                text+= "Laboratorio de apoyo para la parte experimental  e investigativa para el trabajo de circuitos eléctronicos tanto básicos como complejos de los estudiantes de Ingenierías de toda la universidad, contanto con todo el equipo y el personal necesario para su funcionamiento y mantenimiento.";
                text+= "</p></body></html>";
                view.loadDataWithBaseURL(null, text, "text/html", "utf-8", null);
                view.setBackgroundColor(Color.TRANSPARENT);
                break;
            case "labElectrica4":
                srivLab.setImageResource(R.drawable.lab_circuitos_electricos);
                text = "<html><body><h3 align=\"justify\">Laboratorio De Circuitos Eléctricos</h3><p align=\"justify\">";
                text+= "El Laboratorio posee equipos sofisticados de última generación para el desarrollo de actividades práctico experimentales en el área de la electricidad. Dichos equipos como  lo son  osciloscopios digitales, Multímetros auto-rango, fuentes de voltaje DC de alta precisión, permiten llevar a cabo con mayor eficiencia los proyectos y practicas que se pueden realizar.";
                text+= "</p></body></html>";
                view.loadDataWithBaseURL(null, text, "text/html", "utf-8", null);
                view.setBackgroundColor(Color.TRANSPARENT);
                break;
            case "labElectrica5":
                srivLab.setImageResource(R.drawable.lab_digitales);
                text = "<html><body><h3 align=\"justify\">Laboratorio De Digitales</h3><p align=\"justify\">";
                text+= "El laboratorio esta en condiciones de proporcionarle al alumno la formación profesional en el sector industrial de sistemas electrónicos digitales, cuenta con el equipo necesario y el personal calificado para llevar a cabo el proceso de enseñanza mediante prácticas.";
                text+= "</p></body></html>";
                view.loadDataWithBaseURL(null, text, "text/html", "utf-8", null);
                view.setBackgroundColor(Color.TRANSPARENT);
                break;
            case "labElectrica6":
                srivLab.setImageResource(R.drawable.lab_telecomunicaciones);
                text = "<html><body><h3 align=\"justify\">Laboratorio De Telecomunicaciones</h3><p align=\"justify\">";
                text+= "Desde su creación, el Laboratorio de Telecomunicaciones ha sido el lugar de experimentación de variadas temáticas relacionadas a la teoría de telecomunicaciones, sistemas de transmisión y redes de telecomunicaciones que le proporcionan al alumno la formación necesaria para un desempeño profesional de alta calidad.";
                text+= "</p></body></html>";
                view.loadDataWithBaseURL(null, text, "text/html", "utf-8", null);
                view.setBackgroundColor(Color.TRANSPARENT);
                break;
            case "labElectrica7":
                srivLab.setImageResource(R.drawable.lab_alto_voltaje);
                text = "<html><body><h3 align=\"justify\">Laboratorio De Alto Voltaje</h3><p align=\"justify\">";
                text+= "El laboratorio cuenta con el equipo, las instalaciones eléctricas de control e iluminación. Es importante que el lugar cuenta con todas las normas de seguridad necesarias para llevar a cabo todas las prácticas sin que los alumnos corran riesgo alguno.";
                text+= "</p></body></html>";
                view.loadDataWithBaseURL(null, text, "text/html", "utf-8", null);
                view.setBackgroundColor(Color.TRANSPARENT);
                break;
            case "labElectrica8":
                srivLab.setImageResource(R.drawable.lab_control_redes_digitales);
                text = "<html><body><h3 align=\"justify\">Laboratorio De Control y Redes Digitales</h3><p align=\"justify\">";
                text+= "El objetivo principal de este laboratorio es permitir la implementación de estrategias avanzadas de control adaptable robusto tanto entero como fraccionario, control predictivo, estimación de estados, diagnóstico y pronóstico de fallas en sistemas nolineales y no-Gaussianos.";
                text+= "</p></body></html>";
                view.loadDataWithBaseURL(null, text, "text/html", "utf-8", null);
                view.setBackgroundColor(Color.TRANSPARENT);
                break;
            case "labElectrica9":
                srivLab.setImageResource(R.drawable.lab_sincros_robotica);
                text = "<html><body><h3 align=\"justify\">Laboratorio De Sincros y Robótica</h3><p align=\"justify\">";
                text+= "El Laboratorio desarrolla actividades de investigación en robótica móvil y visión computacional. Trabaja principalmente en prácticas de aspectos de percepción, auto-localización, navegación, manipulación, control en tiempo real de movimientos, aprendizaje y simulación de robots móviles.";
                text+= "</p></body></html>";
                view.loadDataWithBaseURL(null, text, "text/html", "utf-8", null);
                view.setBackgroundColor(Color.TRANSPARENT);
                break;
            case "labElectrica10":
                srivLab.setImageResource(R.drawable.lab_control_electrico_plc);
                text = "<html><body><h3 align=\"justify\">Laboratorio De Control Eléctrico y PLC</h3><p align=\"justify\">";
                text+= "El laboratorio cuenta con el equipo necesario para llevar a cabo prácticas de control elétrico (potencia, alta tensión,  baja tensión,  electrónica de potencia y ambientales) además de las prácticas con PLC, hay que mencionar que se cuenta personal necesario para su respectivo uso y mantenimiento.";
                text+= "</p></body></html>";
                view.loadDataWithBaseURL(null, text, "text/html", "utf-8", null);
                view.setBackgroundColor(Color.TRANSPARENT);
                break;
            case "labElectrica11":
                srivLab.setImageResource(R.drawable.lab_mecanismos);
                text = "<html><body><h3 align=\"justify\">Laboratorio De Mecanismos</h3><p align=\"justify\">";
                text+= "El objetivo principal del laboratorio es hacer que el estudiante visualice y evalúe los parámetros relacionados con los mecanismos para el aprendizaje de los principios de la mecánica clásica, mecanismos y vibraciones.";
                text+= "</p></body></html>";
                view.loadDataWithBaseURL(null, text, "text/html", "utf-8", null);
                view.setBackgroundColor(Color.TRANSPARENT);
                break;
            case "labElectrica12":
                srivLab.setImageResource(R.drawable.lab_hidronica_neutronica);
                text = "<html><body><h3 align=\"justify\">Laboratorio De Hidrónica y Neutrónica</h3><p align=\"justify\">";
                text+= "El Laboratorio constituye una de las principales herramientas que permite tanto al docente como al estudiante reafirmar conceptos mediante las prácticas en el area de Hidráulica y Neumática para lo cual el laboratorio esta equipado.";
                text+= "</p></body></html>";
                view.loadDataWithBaseURL(null, text, "text/html", "utf-8", null);
                view.setBackgroundColor(Color.TRANSPARENT);
                break;
            case "labElectrica13":
                srivLab.setImageResource(R.drawable.lab_maquinas_herramientas);
                text = "<html><body><h3 align=\"justify\">Laboratorio De Máquinas y Herramientas</h3><p align=\"justify\">";
                text+= "Tiene como finalidad complementar con prácticas la parte teórica de las carreras de Ingeniería. En este laboratorio, los alumnos aprenden a soldar con soldadura eléctrica, soldadura autógena, procesos de fundición, a manejar las máquinas-herramientas (torno, fresadora, cepillo de codos, etc.)";
                text+= "</p></body></html>";
                view.loadDataWithBaseURL(null, text, "text/html", "utf-8", null);
                view.setBackgroundColor(Color.TRANSPARENT);
                break;
            case "labElectrica14":
                srivLab.setImageResource(R.drawable.lab_electrobombas);
                text = "<html><body><h3 align=\"justify\">Laboratorio De Electrobombas</h3><p align=\"justify\">";
                text+= "El laboratorio está equipado con equipos para las prácticas y el aprendizaje del funcionamientod de las electrobombas, máquinas que tienen como función impulsar el agua a través de tuberías, a distancias o niveles diferentes.";
                text+= "</p></body></html>";
                view.loadDataWithBaseURL(null, text, "text/html", "utf-8", null);
                view.setBackgroundColor(Color.TRANSPARENT);
                break;


            case "labLenguas1":
                srivLab.setImageResource(R.drawable.lab_idiomas);
                text = "<html><body><h3 align=\"justify\">Laboratorio De Idiomas</h3><p align=\"justify\">";
                text+= "Equipado con 30 cabinas, cada una tiene un audífono con micrófono incorporado, cabina central del profesor, equipada con Una computadora con sus elementos completos, además de un amplificador master, pantalla gigante, 2 parlantes ambientales, 1 impresora, 1 infocus, Material de apoyo para cada nivel.";
                text+= "</p></body></html>";
                view.loadDataWithBaseURL(null, text, "text/html", "utf-8", null);
                view.setBackgroundColor(Color.TRANSPARENT);
                break;
            case "labLenguas2":
                srivLab.setImageResource(R.drawable.lab_audio_video);
                text = "<html><body><h3 align=\"justify\">Laboratorio De Audio y Video</h3><p align=\"justify\">";
                text+= "Equipado con ocho Mesas de trabajo, treinta sillones, treinta audífonos con micrófono incorporado, una cabina central del profesor, una computadora con todos los elementos, un amplificador master, una pantalla gigante y un infocus.";
                text+= "</p></body></html>";
                view.loadDataWithBaseURL(null, text, "text/html", "utf-8", null);
                view.setBackgroundColor(Color.TRANSPARENT);
                break;


        }


        // Setup toolbar and statusBar (really FrameLayout)
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Laboratorio");
        statusBar = (FrameLayout) findViewById(R.id.statusBar);

        // Setup navigation drawer
        setupNavigationDrawer();

    }

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
            Intent i = new Intent(LaboratorioInfoActivity.this, BusquedaActivity.class);
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
        LaboratorioInfoActivity.this.getTheme().resolveAttribute(R.attr.colorPrimaryDark, typedValue, true);
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
        linearLayoutManager = new LinearLayoutManager(LaboratorioInfoActivity.this);
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
        linearLayoutManager2 = new LinearLayoutManager(LaboratorioInfoActivity.this);
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
        linearLayoutManager3 = new LinearLayoutManager(LaboratorioInfoActivity.this);
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
        linearLayoutManagerSettings = new LinearLayoutManager(LaboratorioInfoActivity.this);
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
                Intent i = new Intent(LaboratorioInfoActivity.this, DepartamentoInfoActivity.class);
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
                Intent i = new Intent(LaboratorioInfoActivity.this, SitiosActivity.class);
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
                Intent i = new Intent(LaboratorioInfoActivity.this, SitiosActivity.class);
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
                        LaboratorioInfoActivity.this.startActivity(new Intent(LaboratorioInfoActivity.this, LoginActivity.class));
                        break;
                    case 1:
                        LaboratorioInfoActivity.this.startActivity(new Intent(LaboratorioInfoActivity.this, AcercaDeActivity.class));
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



    // All onClick for all views
    @Override
    public void onClick(View v) {

        switch (v.getId()) {



        }
    }
}
