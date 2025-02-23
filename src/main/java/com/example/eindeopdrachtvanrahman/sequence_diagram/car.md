   @PostMapping("/cars")

```mermaid
sequenceDiagram
   participant Client
    participant Controller as CarController
    participant Service as CarService
    participant Repository as CarRepository

    Client->>Controller: POST /cars (CarInputDto)
    Controller->>Service: addCar(dto)
    Service->>Service: transferToCar(dto)
    Service->>Repository: save(car)
    Repository-->>Service: car opgeslagen
    Service->>Service: transferToDTO(car)
    Service-->>Controller: CarDTO
    Controller-->>Client: 201 Created (CarDTO)
  