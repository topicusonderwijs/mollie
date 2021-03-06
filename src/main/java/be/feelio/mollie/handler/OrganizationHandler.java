package be.feelio.mollie.handler;

import be.feelio.mollie.data.organization.OrganizationResponse;
import be.feelio.mollie.exception.MollieException;
import be.feelio.mollie.util.ObjectMapperService;
import be.feelio.mollie.util.QueryParams;
import kong.unirest.HttpResponse;
import kong.unirest.UnirestException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * Handles the organizations API <a href="https://docs.mollie.com/reference/v2/organizations-api/current-organization">Mollie docs</a>
 *
 * @author Wout Schoovaerts
 */
public class OrganizationHandler extends AbstractHandler {

    private static final Logger log = LoggerFactory.getLogger(OrganizationHandler.class);

    public OrganizationHandler(String baseUrl) {
        super(baseUrl, log);
    }

    /**
     * Retrieve the currently authenticated organization.
     *
     * @return OrganizationResponse object
     * @throws MollieException when something went wrong
     */
    public OrganizationResponse getMyOrganization() throws MollieException {
        return getMyOrganization(QueryParams.EMPTY);
    }

    /**
     * Retrieve the currently authenticated organization.
     *
     * @param params a map of query params
     * @return OrganizationResponse object
     * @throws MollieException when something went wrong
     */
    public OrganizationResponse getMyOrganization(QueryParams params) throws MollieException {
        try {
            String uri = "/organizations/me";

            HttpResponse<String> response = get(uri, params);

            return ObjectMapperService.getInstance().getMapper()
                    .readValue(response.getBody(), OrganizationResponse.class);
        } catch (UnirestException | IOException ex) {
            log.error("An unexpected exception occurred", ex);
            throw new MollieException(ex);
        }
    }

    /**
     * Retrieve an organization by its ID.
     * <p>
     * If you do not know the organization’s ID, you can use the organizations list endpoint to retrieve all organizations that are accessible.
     *
     * @param organizationId An organization ID
     * @return OrganizationResponse object
     * @throws MollieException when something went wrong
     */
    public OrganizationResponse getOrganization(String organizationId) throws MollieException {
        return getOrganization(organizationId, QueryParams.EMPTY);
    }

    /**
     * Retrieve an organization by its ID.
     * <p>
     * If you do not know the organization’s ID, you can use the organizations list endpoint to retrieve all organizations that are accessible.
     *
     * @param organizationId An organization ID
     * @param params         a map of query params
     * @return OrganizationResponse object
     * @throws MollieException when something went wrong
     */
    public OrganizationResponse getOrganization(String organizationId, QueryParams params) throws MollieException {
        try {
            String uri = "/organizations/" + organizationId;

            HttpResponse<String> response = get(uri, params);

            return ObjectMapperService.getInstance().getMapper()
                    .readValue(response.getBody(), OrganizationResponse.class);
        } catch (UnirestException | IOException ex) {
            log.error("An unexpected exception occurred", ex);
            throw new MollieException(ex);
        }
    }
}
