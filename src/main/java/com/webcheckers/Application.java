/*
 * The entry point for the WebCheckers web application.
 *
 * @author Eugene Chang
 * @author Karthik Iyer
 * @author Gaurav Pant
 */


package com.webcheckers;


import java.io.InputStream;
import java.util.Objects;
import java.util.logging.LogManager;
import java.util.logging.Logger;


import com.webcheckers.appl.GlobalInformation;
import com.webcheckers.ui.WebServer;
import spark.TemplateEngine;
import spark.template.freemarker.FreeMarkerEngine;


public final class Application {

    private static final Logger LOG = Logger.getLogger(Application.class.getName());


    /**
     * Entry point for the WebCheckers web application.
     * It wires the application components together.
     * @param args Command line arguments; none expected.
     */
    public static void main( String[] args ) {

        // initialize Logging
        try {
            ClassLoader classLoader = Application.class.getClassLoader();
            final InputStream logConfig = classLoader.getResourceAsStream( "log.properties" );
            LogManager.getLogManager().readConfiguration( logConfig );
        } catch( Exception e ) {
            e.printStackTrace();
            System.err.println( "Could not initialize log manager because: " + e.getMessage() );
        }

        // the global info
        final GlobalInformation gi = new GlobalInformation();

        // The application uses FreeMarker templates to generate the HTML
        // responses sent back to the client. This will be the engine processing
        // the templates and associated data.
        final TemplateEngine templateEngine = new FreeMarkerEngine();

        // inject the game center and freemarker engine into web server
        final WebServer webServer = new WebServer( templateEngine, gi );

        // inject web server into application
        final Application app = new Application( webServer );

        // start the application up
        app.initialize();
    }

    // Attributes
    private final WebServer webServer;


    /**
     * Constructor for the Application
     * @param webServer the web server
     */
    private Application( final WebServer webServer ) {
        // validation
        Objects.requireNonNull( webServer, "webServer must not be null" );

        this.webServer = webServer;
    }


    /**
     * Application initializer
     * Starts up and configures everything
     */
    private void initialize() {

        LOG.config( "WebCheckers is initializing." );

        // configure Spark and startup the Jetty web server
        webServer.initialize();

        // other applications might have additional services to configure

        LOG.config( "WebCheckers initialization complete." );
    }

}