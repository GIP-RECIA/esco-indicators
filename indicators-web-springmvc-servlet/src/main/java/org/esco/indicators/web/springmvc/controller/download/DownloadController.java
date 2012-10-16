/**
 * 
 */
package org.esco.indicators.web.springmvc.controller.download;

import java.io.IOException;
import java.io.Serializable;
import java.net.SocketException;
import java.util.HashMap;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.esupportail.commons.utils.Assert;
import org.esupportail.commons.web.servlet.DownloadServlet;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Controller used to send data to download to the user.
 * 
 * @since  2012/10/15
 * @author GIP RECIA - Kevin Frapin <kevin.frapin@recia.fr>
 */
@Controller
public class DownloadController extends DownloadServlet implements Serializable, InitializingBean {
    //---------------------------------------------------------------------------------- ATTRIBUTES
    /** Auto generated UID */
    private static final long serialVersionUID = -1818266578409800502L;
    
    /** Logger of the class */
    private static final Logger LOGGER = Logger.getLogger(DownloadController.class);
    
    /** Content types by file extensions */
    @Autowired
    private HashMap<String, String> contentTypesByExtension;
    
    /** Default content type to use */
    @Autowired
    private String defaultContentType;

    //-------------------------------------------------------------------------------- CONSTRUCTORS

    /* (non-Javadoc)
     * @see org.springframework.beans.factory.InitializingBean#afterPropertiesSet()
     */
    @Override
    public void afterPropertiesSet() throws Exception {
	Assert.notNull(contentTypesByExtension, "property contentTypesByExtension of class "
		+ this.getClass().getName() + " can not be null");
	LOGGER.debug("The content types by extensions are : " + contentTypesByExtension);
	Assert.notNull(defaultContentType, "property defaultContentType of class "
		+ this.getClass().getName() + " can not be null");
	LOGGER.debug("The default content type is : [" + defaultContentType + "]");
    }

    //--------------------------------------------------------------------------- GETTERS / SETTERS
    /**
     * Sets the map containing the content types indexed by file extensions.
     * 
     * @param contentTypesByExtension 
     * 			The content types to set.
     */
    public void setContentTypesByExtension(HashMap<String, String> contentTypesByExtension) {
        this.contentTypesByExtension = contentTypesByExtension;
    }

    /**
     * The default content type used when no content type can be determined.
     * 
     * @param defaultContentType 
     * 			The default content type to set.
     */
    public void setDefaultContentType(String defaultContentType) {
        this.defaultContentType = defaultContentType;
    }
    
    
    //------------------------------------------------------------------------------ PUBLIC METHODS

    /**
     * Creates a data stream from the data received by the user and sent that stream back to the user in a file.<br/>
     * This method will ask the user to download a file containing the data.<br/>
     *
     * @param data
     * 			The data to send to the user.
     * @param fileName
     * 			The name of the file to download.
     * @param response
     * 			The response servlet used to send the data.
     */
    @RequestMapping(value="/download-data-ajax")
    public void downloadData(@RequestParam String data, @RequestParam String fileName, HttpServletResponse response) {
	// Retrieve the data send by the user
	LOGGER.debug("Data send by the user and that will be downloaded : [" + data + "]" );
	LOGGER.debug("The file to download : [" + fileName + "]" );

	// Sets the header of the response
	String fileExtension  = extractExtension(fileName);
	
	setContentType(response, fileExtension);
	response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
	response.setHeader("Content-Description", "File transfert");
	LOGGER.debug("The data length is : [" + data.length() + "]");
	response.setContentLength(data.length());
	response.setCharacterEncoding("UTF-8");
	response.setHeader("Cache-Control", ": max-age=60, must-revalidate");
	
	// Sets the cookie for the download
	response.setHeader("Set-Cookie", "fileDownload=true");
	
	// Sends the data
	try {
	    	ServletOutputStream out = response.getOutputStream();
	    	byte [] byteData = data.getBytes();
		out.write(byteData);
	} catch (SocketException e) {
		LOGGER.warn("SocketException was raides while downloading, probably because the client cancelled : " + e.getMessage());
	} catch (IOException e) {
	    LOGGER.error("An error occured during the retrieval of the response output stream : " + e.getMessage());
	}
    }
    
    //----------------------------------------------------------------------------- PRIVATE METHODS
    /**
     * Sets the content type into the {@link HttpServletResponse} regarding to the 
     * given file extension and the defined contentTypesByExtension.<br/>
     * 
     * If the file extension is unknown, then the content type defaultContentType will be set.
     * 
     * @param response
     * 			The response used to set the content type.
     * @param fileExtension
     * 			The file extension.
     */
    private void setContentType(HttpServletResponse response, String fileExtension) {
	// Try to find the associated content type
	String contentType = contentTypesByExtension.get(fileExtension);
	if(contentType == null) {
	    contentType = defaultContentType;
	}
	// Sets the content type
	LOGGER.debug("The content type for the file extension : [" +  fileExtension +"] is : [" + contentType + "]");
	response.setContentType(contentType);
    }
    
    /**
     * Extracts the extension of the given name.
     * 
     * @param fileName
     * 			The file name to analyze.
     * 
     * @return
     * 	The extension of the file name.<br/>
     * 	An empty string if no extension has been extracted.
     */
    private String extractExtension(String fileName) {
	// If the file name is null
	if(fileName == null) {
	    return "";
	}
	
	// Splits the name at each dot
	String [] splitName =fileName.split("\\.");
	// If an extension has been extract
	if(splitName.length > 1) {
	    return splitName[splitName.length - 1];
	}
	return "";
    }

    //------------------------------------------------------------------------------ STATIC METHODS

}
