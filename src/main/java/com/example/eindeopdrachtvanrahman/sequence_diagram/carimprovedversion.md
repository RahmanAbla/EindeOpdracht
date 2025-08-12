@PostMapping("/cars")

```mermaid
sequenceDiagram
   actor Client
    participant Controller as CarController
    participant Service as CarService
    participant Repository as CarRepository
    participant DTO as CarDTO
    participant Model as Car

    Client->>Controller: POST /cars (CarInputDto)
    Controller->>Service: addCar(CarInputDto dto)
    Service->>Model: new Car()
    Service->>Model: setBrand(dto.brand)
    Service->>Model: setModel(dto.model)
    Service->>Model: setManufacturingyear(dto.manufacturingyear)
    Service->>Model: setId(dto.id)
    Service->>Repository: save(Car)
    Repository-->>Service: saved Car
    Service->>DTO: new CarDTO()
    Service->>DTO: setBrand(car.brand)
    Service->>DTO: setModel(car.model)
    Service->>DTO: setManufacturingyear(car.manufacturingyear)
    Service->>DTO: setId(car.id)
    Service-->>Controller: CarDTO
    Controller-->>Client: 201 Created (CarDTO)