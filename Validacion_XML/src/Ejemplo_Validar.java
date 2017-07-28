import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.util.List;

import javax.xml.XMLConstants;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;

import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
/**
 * @author xe66670
 *
 */
public class Ejemplo_Validar {
	
	private static void error(final String msg) {
		System.err.println("- ERROR - " + msg);
	}

	private static void info(final String msg) {
		System.out.println("- INFO - " + msg);
	}


	/**
	 * @param args
	 * @throws SAXException 
	 */
	public static void main(String[] args) throws SAXException {
		
		
		final String nfxml="Country_Sin_Divisa.xml";//"libro.xml";
		final String nfxsd="CalypsoUploadDocument.xsd";//"libro.xsd";
		/** The error handler. */
		SAXErrorHandler errorHandler = null;
		
		errorHandler = new SAXErrorHandler();
		
		// create a SchemaFactory capable of understanding WXS schemas
	    SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);

	    // load a WXS schema, represented by a Schema instance
	    Source schemaFile = new StreamSource(new File(nfxsd));
	    Schema schema = factory.newSchema(schemaFile);

	    // create a Validator instance, which can be used to validate an instance document
	    Validator validator = schema.newValidator();


	 // XML a validar
	 		final Source xml = new StreamSource(new File (nfxml));

	 		// Definición del manejador de excepciones del validador
	 		errorHandler.clear();
	 		validator.setErrorHandler(errorHandler);
	 		
	 	// Validación del XML
			try {
				validator.validate(xml);
			} catch (final IOException e) {
				error(e.toString());
			}

			// Show results
			final List<SAXParseException> exceptions = errorHandler.getExceptions();
			if (exceptions.isEmpty()) {
				info("" + ":" + nfxml + ": OK");
			} else {
				for (final SAXParseException exception : exceptions) {
					final StringBuilder sb = new StringBuilder("[");
					sb.append(exception.getLineNumber());
					sb.append(", ");
					sb.append(exception.getColumnNumber());
					sb.append("] ");
					sb.append(exception);
					error(sb.toString());
				}
			}
	 		
	    
	    
	}

}
