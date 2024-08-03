package pet.project.licensingservice.external.client;

import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class OrganizationClientProvider {

    private final Map<ClientType, OrganizationClient> clientMap;

    public OrganizationClientProvider(List<OrganizationClient> clients) {
        clientMap = clients
                .stream()
                .collect(
                        Collectors.toMap(
                                OrganizationClient::getClientType,
                                Function.identity()
                        )
                );
    }

    public OrganizationClient getByClientType(ClientType clientType) {
        return clientMap.get(clientType);
    }

}
