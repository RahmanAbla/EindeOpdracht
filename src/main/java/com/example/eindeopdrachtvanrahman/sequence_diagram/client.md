#   @GetMapping("/clients")

```mermaid
sequenceDiagram
   participant Client
    participant Controller as ClientController
    participant Service as ClientService
    participant Repository as ClientRepository

    Client->>Controller: GET /clients
    Controller->>Service: getAllClients()
    Service->>Repository: findAll()
    Repository-->>Service: List<Client>
    loop Over Client List
        Service->>Service: transferToDTO(client)
    end
    Service-->>Controller: List<ClientDTO>
    Controller-->>Client: 200 OK (List<ClientDTO>)
  
    
     

     




