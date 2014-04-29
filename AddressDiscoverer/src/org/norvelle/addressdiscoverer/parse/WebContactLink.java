/**
 * Part of the AddressDiscoverer project, licensed under the GPL v.3 license.
 * This project provides intelligence for discovering email addresses in
 * specified web pages, associating them with a given institution and department
 * and address type.
 *
 * This project is licensed under the GPL v.3. Your rights to copy and modify
 * are regulated by the conditions specified in that license, available at
 * http://www.gnu.org/licenses/gpl-3.0.html
 */
package org.norvelle.addressdiscoverer.parse;

import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.commons.io.IOUtils;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.norvelle.addressdiscoverer.Constants;
import org.norvelle.addressdiscoverer.exceptions.DoesNotContainContactLinkException;
import org.norvelle.addressdiscoverer.exceptions.MultipleContactLinksOfSameTypeFoundException;
import org.norvelle.addressdiscoverer.exceptions.NoContactLinkFoundException;

/**
 *
 * @author Erik Norvelle <erik.norvelle@cyberlogos.co>
 */
public class WebContactLink extends ContactLink {
    
    protected static final Pattern weblinkPattern = Pattern.compile(Constants.weblinkRegex);
    
    /**
     * Attempt to find a URL-type link associated with the given Jsoup Element,
     * by looking at all the HREF attributes of the various subelements.
     * 
     * @param element
     * @throws DoesNotContainContactLinkException
     * @throws MultipleContactLinksOfSameTypeFoundException 
     */
    public WebContactLink(Element element) 
            throws DoesNotContainContactLinkException, 
            MultipleContactLinksOfSameTypeFoundException 
    {
        super(element);
        ArrayList<String> hrefs = new ArrayList();
        Elements elements = element.getAllElements();
        for (Element child : elements) {
            if (child.hasAttr("href")) {
                String href = child.attr("href");
                if (!href.startsWith("mailto:"))
                    hrefs.add(href);
            }
        }
        
        if (hrefs.isEmpty())
            throw new DoesNotContainContactLinkException();
        else if (hrefs.size() > 1)
            throw new MultipleContactLinksOfSameTypeFoundException();
        this.address = hrefs.get(0);
    }

   /**
     * Fetches the web page specified by the contact weblink and extracts
     * an email from it. The email gets stored in the address field for retrieval
     * by the Individual extractor. Note that we fetch the first such email found
     * and discard others.
     * 
     * @throws NoContactLinkFoundException 
     */
    public void fetchEmailFromWeblink()  {
        String body;
        
        // Try to fetch the webpage linked to
        try {
            URL u = new URL(this.address); // this would check for the protocol
            u.toURI();
            URLConnection con = u.openConnection();
            InputStream in = con.getInputStream();
            String encoding = con.getContentEncoding();
            encoding = encoding == null ? "UTF-8" : encoding;
            body = IOUtils.toString(in, encoding);
        } catch (URISyntaxException | IOException ex) {
            return;
        }
        
        // Now, extract the email if we can.
        String matchFound = this.findEmail(body);
        if (matchFound.isEmpty()) {
            return;                
        }
        this.address = matchFound;
    }
    
    private String findEmail(String text) {
        Matcher emailMatcher = emailPattern.matcher(text);
        String matchFound = "";
        while (emailMatcher.find()) {
            matchFound = text.substring(emailMatcher.start(), emailMatcher.end());
            break;
        }        
        return matchFound;
    }
    
    @Override
    public String toString() {
        return String.format("URL: %s", this.address);
    }

}