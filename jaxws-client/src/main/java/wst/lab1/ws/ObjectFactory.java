
package wst.lab1.ws;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the wst.lab1.ws package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _FilterResponse_QNAME = new QName("http://ws.lab1.wst/", "filterResponse");
    private final static QName _FindAllResponse_QNAME = new QName("http://ws.lab1.wst/", "findAllResponse");
    private final static QName _Create_QNAME = new QName("http://ws.lab1.wst/", "create");
    private final static QName _CreateResponse_QNAME = new QName("http://ws.lab1.wst/", "createResponse");
    private final static QName _Update_QNAME = new QName("http://ws.lab1.wst/", "update");
    private final static QName _UpdateResponse_QNAME = new QName("http://ws.lab1.wst/", "updateResponse");
    private final static QName _Delete_QNAME = new QName("http://ws.lab1.wst/", "delete");
    private final static QName _FindAll_QNAME = new QName("http://ws.lab1.wst/", "findAll");
    private final static QName _Filter_QNAME = new QName("http://ws.lab1.wst/", "filter");
    private final static QName _ServiceException_QNAME = new QName("http://ws.lab1.wst/", "ServiceException");
    private final static QName _DeleteResponse_QNAME = new QName("http://ws.lab1.wst/", "deleteResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: wst.lab1.ws
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link Filter }
     * 
     */
    public Filter createFilter() {
        return new Filter();
    }

    /**
     * Create an instance of {@link ServiceFault }
     */
    public ServiceFault createServiceFault() {
        return new ServiceFault();
    }

    /**
     * Create an instance of {@link DeleteResponse }
     *
     */
    public DeleteResponse createDeleteResponse() {
        return new DeleteResponse();
    }

    /**
     * Create an instance of {@link Create }
     *
     */
    public Create createCreate() {
        return new Create();
    }

    /**
     * Create an instance of {@link CreateResponse }
     *
     */
    public CreateResponse createCreateResponse() {
        return new CreateResponse();
    }

    /**
     * Create an instance of {@link Update }
     *
     */
    public Update createUpdate() {
        return new Update();
    }

    /**
     * Create an instance of {@link FilterResponse }
     *
     */
    public FilterResponse createFilterResponse() {
        return new FilterResponse();
    }

    /**
     * Create an instance of {@link FindAllResponse }
     *
     */
    public FindAllResponse createFindAllResponse() {
        return new FindAllResponse();
    }

    /**
     * Create an instance of {@link Delete }
     *
     */
    public Delete createDelete() {
        return new Delete();
    }

    /**
     * Create an instance of {@link FindAll }
     *
     */
    public FindAll createFindAll() {
        return new FindAll();
    }

    /**
     * Create an instance of {@link UpdateResponse }
     *
     */
    public UpdateResponse createUpdateResponse() {
        return new UpdateResponse();
    }

    /**
     * Create an instance of {@link Menagerie }
     *
     */
    public Menagerie createMenagerie() {
        return new Menagerie();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link FilterResponse }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://ws.lab1.wst/", name = "filterResponse")
    public JAXBElement<FilterResponse> createFilterResponse(FilterResponse value) {
        return new JAXBElement<FilterResponse>(_FilterResponse_QNAME, FilterResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link FindAllResponse }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://ws.lab1.wst/", name = "findAllResponse")
    public JAXBElement<FindAllResponse> createFindAllResponse(FindAllResponse value) {
        return new JAXBElement<FindAllResponse>(_FindAllResponse_QNAME, FindAllResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Create }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://ws.lab1.wst/", name = "create")
    public JAXBElement<Create> createCreate(Create value) {
        return new JAXBElement<Create>(_Create_QNAME, Create.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CreateResponse }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://ws.lab1.wst/", name = "createResponse")
    public JAXBElement<CreateResponse> createCreateResponse(CreateResponse value) {
        return new JAXBElement<CreateResponse>(_CreateResponse_QNAME, CreateResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Update }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://ws.lab1.wst/", name = "update")
    public JAXBElement<Update> createUpdate(Update value) {
        return new JAXBElement<Update>(_Update_QNAME, Update.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link UpdateResponse }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://ws.lab1.wst/", name = "updateResponse")
    public JAXBElement<UpdateResponse> createUpdateResponse(UpdateResponse value) {
        return new JAXBElement<UpdateResponse>(_UpdateResponse_QNAME, UpdateResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Delete }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://ws.lab1.wst/", name = "delete")
    public JAXBElement<Delete> createDelete(Delete value) {
        return new JAXBElement<Delete>(_Delete_QNAME, Delete.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link FindAll }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://ws.lab1.wst/", name = "findAll")
    public JAXBElement<FindAll> createFindAll(FindAll value) {
        return new JAXBElement<FindAll>(_FindAll_QNAME, FindAll.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Filter }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://ws.lab1.wst/", name = "filter")
    public JAXBElement<Filter> createFilter(Filter value) {
        return new JAXBElement<Filter>(_Filter_QNAME, Filter.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ServiceFault }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://ws.lab1.wst/", name = "ServiceException")
    public JAXBElement<ServiceFault> createServiceException(ServiceFault value) {
        return new JAXBElement<ServiceFault>(_ServiceException_QNAME, ServiceFault.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DeleteResponse }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://ws.lab1.wst/", name = "deleteResponse")
    public JAXBElement<DeleteResponse> createDeleteResponse(DeleteResponse value) {
        return new JAXBElement<DeleteResponse>(_DeleteResponse_QNAME, DeleteResponse.class, null, value);
    }

}
