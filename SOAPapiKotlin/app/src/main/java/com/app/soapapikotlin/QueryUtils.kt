package com.davenotdavid.soapsample

import android.util.Log
import org.ksoap2.SoapEnvelope
import org.ksoap2.serialization.SoapObject
import org.ksoap2.serialization.SoapSerializationEnvelope
import org.ksoap2.transport.HttpTransportSE
import org.w3c.dom.Element
import org.w3c.dom.Node
import org.xml.sax.InputSource
import java.io.StringReader
import javax.xml.parsers.DocumentBuilderFactory

/**
 * Object/singleton class that consists of utility helper methods for making a request for
 * extracting data (cities) from an XML response of a SOAP web service.
 */
object QueryUtils {

    // Log tag constant.
    private val LOG_TAG = QueryUtils::class.java.simpleName

    // Elements of a SOAP envelop body.
    private val NAMESPACE = "http://www.webserviceX.NET"
    private val METHOD_NAME = "GetCitiesByCountry"

    // SOAP Action header field URI consisting of the namespace and method that's used to make a
    // call to the web service.
    private val SOAP_ACTION = NAMESPACE + "/" + METHOD_NAME

    // Web service URL (that should be openable) along with the Web Service Definition Language
    // (WSDL) that's used to view the WSDL file by simply adding "?WSDL" to the end of the URL.
    private val URL = "http://www.webservicex.net/globalweather.asmx?WSDL"

    /**
     * Helper method that requests response data from a web service, and later returns a list of
     * data (cities).
     */
    fun fetchCitiesData(userInput: String): List<String>? {

        // A simple dynamic object that can be used to build SOAP calls without
        // implementing KvmSerializable. Essentially, this is what goes inside the body of
        // a SOAP envelope - it is the direct subelement of the body and all further sub
        // elements. Instead of this class, custom classes can be used if they implement
        // the KvmSerializable interface.
        val request = SoapObject(NAMESPACE, METHOD_NAME)

        // The following adds a parameter (parameter name, user inputted value).
        request.addProperty("CountryName", userInput)

        // Declares the version of the SOAP request.
        val envelope = SoapSerializationEnvelope(SoapEnvelope.VER12)

        // Set the following variable to true for compatibility with what seems to be the
        // default encoding for .Net-Services.
        envelope.dotNet = true

        // Assigns the SoapObject to the envelope as the outbound message for the SOAP call.
        envelope.setOutputSoapObject(request)

        // A J2SE based HttpTransport layer instantiation of the web service URL and the
        // WSDL file.
        val httpTransport = HttpTransportSE(URL)
        try {

            // This is the actual part that will call the webservice by setting the desired
            // header SOAP Action header field.
            httpTransport.call(SOAP_ACTION, envelope)

            // Returns a list of data (cities) after extracting data from the XML response.
            return extractDataFromXmlResponse(envelope)
        } catch (e: Exception) { // Many kinds of exceptions can be caught here
            Log.e(LOG_TAG, e.toString())
        }

        // Otherwise, returns null.
        return null
    }

    /**
     * Extracts data (cities) from the XML response, and ultimately returns a list of cities.
     */
    @Throws(Exception::class)
    private fun extractDataFromXmlResponse(envelope: SoapSerializationEnvelope): List<String> {

        // Initializes a list to add elements (cities).
        val citiesList = mutableListOf<String>()

        // Initializes/instantiates a DocumentBuilder to parse the response from the SOAP envelope
        // in order to build an XML object, or a Document in this case.
        val docBuildFactory = DocumentBuilderFactory.newInstance()
        val docBuilder = docBuildFactory.newDocumentBuilder()
        val doc = docBuilder.parse(InputSource(StringReader(envelope.response.toString())))

        // Retrieves a list of Table nodes from the Document in order to iterate through.
        val nodeList = doc.getElementsByTagName("Table")
        for (i in 0..nodeList.length - 1) {

            // Retrieves each Table node.
            val node = nodeList.item(i)

            // Runs the following functionality should the node be of an element type.
            if (node.nodeType == Node.ELEMENT_NODE) {

                // Initially casts the node as an element.
                val element = node as Element

                // Adds each city to the list.
                citiesList.add(element.getElementsByTagName("City").item(0).textContent)
            }
        }

        return citiesList
    }
}
