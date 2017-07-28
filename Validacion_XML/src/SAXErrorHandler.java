

import java.util.LinkedList;
import java.util.List;

import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

public class SAXErrorHandler implements ErrorHandler {
	final List<SAXParseException> exceptions = new LinkedList<SAXParseException>();

	@Override
	public void warning(final SAXParseException exception) throws SAXException {
		exceptions.add(exception);
	}

	@Override
	public void error(final SAXParseException exception) throws SAXException {
		exceptions.add(exception);
	}

	@Override
	public void fatalError(final SAXParseException exception) throws SAXException {
		exceptions.add(exception);
	}

	public List<SAXParseException> getExceptions() {
		return exceptions;
	}

	public void clear() {
		exceptions.clear();
	}
}
