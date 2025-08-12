#   @GetMapping("/clients")

```mermaid
sequenceDiagram
    actor ClientApp as Client
    participant Controller as ClientController
    participant Service as ClientService
    participant Repository as ClientRepository
    participant Entity as Client
    participant DTO as ClientDTO

    ClientApp->>Controller: GET /clients
    Controller->>Service: getAllClients()
    Service->>Repository: findAll()
    Repository-->>Service: List<Client>

    loop for each Client
        Service->>Service: transferToDTO(Client)
        Service->>DTO: maak nieuwe ClientDTO
        Service->>DTO: kopieer gegevens (bijv. id) vanuit Client
    end

    Service-->>Controller: List<ClientDTO>
    Controller-->>ClientApp: 200 OK (List<ClientDTO>)