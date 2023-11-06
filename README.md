# Parking Tech

## Desafios
- Tratamento de erros e exceções: Foi solicitado que fosse feito a validação dos dados e o tratamento de exceções retornando ao usuário uma mensagem informativa, este acabou sendo o primeiro desafio encontrado, pois existem muitas formas de tratar o erro e retornar ao usuário, a discussão foi sobre qual estratégia seguir. A primeira tentativa foi utilizando o binding result que aparentemente era uma boa opção, mas começamos a notar muita repetição de código e já começamos a comparar alternativas. A outra opção que pareceu melhor para o resultado que queríamos foi utilizar um handler geral para capturar erros com o @ExceptionHandler e o @RestControllerAdvice, que nos permitiu tratar as exceções de forma mais genérica, inclusive exceções de validação, em um único ponto.
- Entender como funciona um sistema de parquímetro e modelar de uma forma que ficasse funcional
- Lógica no controller ou service: Discussão sobre onde a lógica deveria estar surgiu na metade final do desenvolvimento, pois como ainda estamos trabalhando em um sistema pequeno a lógica é bem simples e curta, por isso estava direto no controller. Pensando que este sistema tende a crescer e sofrer alterações não é boa prática a lógica permanecer no controller, pois isso acaba gerando um débito técnico levando em consideração o princípio da responsabilidade única.
- Análise de onde seria positivo usar o Redis para cache

## Tecnologias

- Como linguagem base usamos Java na versão 17 LTS.
- O Framework utilizado foi o Spring Boot que simplifica a configuração e o desenvolvimento.
    - Spring Web MVC para tratar as requisições HTTP, fazer o mapeamento de URLs e a comunicação entre a camada de apresentação e a camada de negócio.
    - O Spring Validation foi utilizado para validar os dados de entrada da API, para assegurar a integridade dos dados.
    - O Spring Data JPA foi utilizado para facilitar a persistência dos dados.
- A biblioteca Springdoc OpenAPI foi utilizada para facilitar a geração da documentação do projeto.
- O SQL foi utilizado para buscas e ações mais avançadas não disponíveis diretamente no Spring Data JPA.
- As tecnologias Docker e Docker Compose foram usadas para fazer a conteinerização
- Spring Data Redis foi utilizado para cachear informações e reduzir o estresse do banco

## Ferramentas
- IntelliJ IDEA
- Insomnia
- Git
- Maven
- Copilot
- Spring initializr
- MySQL
- Docker
- Docker Compose
- Redis

## Documentação das APIs

### Como executar o projeto
- executar `docker compose up` dentro da raiz do projeto e ao terminar de subir os containers subir o projeto normalmente


- ### API de Endereços:

  <details>
   <summary>Cadastrar um endereço</summary>

    - POST: http://localhost:8080/addresses
        - Request:
          ```bash
            curl -X POST 'localhost:8080/addresses' \
            -H 'Content-Type: application/json' \
            --data '{
              "street": "Estrada da Madeira",
              "number": "100",
              "neighborhood": "Barragem",
              "city": "Rio do Sul",
              "state": "SC"
            }'
          ```
        - Response 201:
          ```json
            {
              "id": 8,
              "street": "Estrada da Madeira",
              "number": "100",
              "neighborhood": "Barragem",
              "city": "Rio do Sul",
              "state": "SC"
            }
          ```
        - Response 400:
          ```json
            {
              "timestamp": "2023-09-22T01:43:33.426543892Z",
              "status": 400,
              "message": "ocorreu um ou mais erros de validação",
              "path": "/addresses",
              "invalidParams": [
                {
                  "field": "city",
                  "message": "não deve estar em branco"
                },
                {
                  "field": "state",
                  "message": "não deve estar em branco"
                },
                {
                  "field": "driverId",
                  "message": "não deve ser nulo"
                },
                {
                  "field": "number",
                  "message": "não deve estar em branco"
                },
                {
                  "field": "number",
                  "message": "tamanho deve ser entre 1 e 4"
                },
                {
                  "field": "neighborhood",
                  "message": "não deve estar em branco"
                },
                {
                  "field": "street",
                  "message": "não deve estar em branco"
                }
              ]
            }
          ```
        - Response 404:
          ```json
            {
              "timestamp": "2023-09-22T01:44:37.350570675Z",
              "status": 404,
              "message": "Condutor não encontrado, id: 51",
              "path": "/addresses"
            }
          ```
  </details>

  <details>
    <summary>Buscar todos os endereços</summary>

    - GET: http://localhost:8080/addresses
        - Request:
          ```bash
            curl -X GET 'localhost:8080/addresses'
          ```
        - Response 200:
          ```json
          [
            {
              "id": 1,
              "street": "Avenida Rio do Grande sul",
              "number": "4748",
              "neighborhood": "Navegantes",
              "city": "Porto Alegre",
              "state": "RS"
            },
            {
              "id": 4,
              "street": "Avenida Mendonça Júnior 126",
              "number": "762",
              "neighborhood": "Central",
              "city": "Macapá",
              "state": "AP"
            }
          ]
          ```
  </details>

  <details>
    <summary>Buscar um endereço</summary>

    - GET: http://localhost:8080/addresses/{id} *(id do endereço buscado)*
        - Request:
          ```bash
            curl -X GET 'localhost:8080/addresses/1'
          ```
        - Response 200:
          ```json
            {
              "id": 1,
              "street": "Avenida Rio do Grande sul",
              "number": "4748",
              "neighborhood": "Navegantes",
              "city": "Porto Alegre",
              "state": "RS"
            }
          ```
        - Response 404:
          ```json
            {
              "timestamp": "2023-09-22T02:01:28.566949163Z",
              "status": 404,
              "message": "Endereço não encontrado, id: 20",
              "path": "/addresses/20"
            }
          ```
  </details>  

  <details>
    <summary>Atualizar um endereço</summary>

    - PUT: http://localhost:8080/addresses/{id} *(id do endereço a ser atualizado)*
        - Request:
          ```bash
            curl -X PUT 'localhost:8080/addresses/1' \
            -H 'Content-Type: application/json' \
            --data '{
              "id": 3,
              "street": "Avenida Rio do Grande sul",
              "number": "4748",
              "neighborhood": "Navegantes",
              "city": "Porto Alegre",
              "state": "RS",
              "driverId": 4
            }'
          ```
        - Response 200:
          ```json        
            {
              "id": 4,
              "street": "Avenida Rio do Grande sul",
              "number": "4748",
              "neighborhood": "Navegantes",
              "city": "Porto Alegre",
              "state": "RS",
              "driver": {
                "id": 3,
                "name": "Valentina Malu Melo",
                "driverLicense": "93694660327",
                "email": "valentinamalumelo@cenavip.com.br",
                "mobileNumber": "85992383628"
              }
            }
          ```
        - Response 404:
          ```json
            {
              "timestamp": "2023-09-22T02:11:15.018427121Z",
              "status": 400,
              "message": "ocorreu um ou mais erros de validação",
              "path": "/addresses/4",
              "invalidParams": [
                {
                  "field": "driverId",
                  "message": "não deve ser nulo"
                }
              ]
            }
          ```
  </details>

  <details>
    <summary>Deletar um endereço</summary>

    - DELETE: http://localhost:8080/addresses/{id} *(id do endereço a ser deletado)*
        - Request:
          ```bash
            curl -X DELETE 'localhost:8080/addresses/1'
          ```
        - Response 204:
          ```json
            {}
          ```
        - Response 404:
          ```json
            {
              "timestamp": "2023-09-22T01:44:37.350570675Z",
              "status": 404,
              "message": "Condutor não encontrado, id: 51",
              "path": "/addresses"
            }
          ```      
  </details>

- ### API de Condutores

  <details>
    <summary>Cadastrar um condutor</summary>

    - POST: http://localhost:8080/drivers
      - Request:
        ```bash
          curl -X POST 'localhost:8080/drivers' \
          -H 'Content-Type: application/json' \
          --data '{
            "name":"Gabriel Martin Costa",
            "driverLicense":"49747790304",
            "email":"gabriel_costa@vegacon.com.br",
            "mobileNumber":"27988099520",
            "adressesIds":[1],
            "preferredPayment":"PIX"
          }'
        ```
      - Response 201:
        ```json
          {
            "id": 5,
            "name": "Gabriel Martin Costa",
            "driverLicense": "49747790304",
            "email": "gabriel_costa@vegacon.com.br",
            "mobileNumber": "27988099520",
            "addresses":[{"id":1,"street":"Estrada da Madeira","number":"100","neighborhood":"Barragem","city":"Rio do Sul","state":"SC"}]
          }
        ```
      - Response 400:
        ```json
          {
            "timestamp": "2023-09-22T01:10:32.680793494Z",
            "status": 400,
            "message": "ocorreu um ou mais erros de validação",
            "path": "/drivers",
            "invalidParams": [
              {
                "field": "email",
                "message": "não deve estar em branco"
              },
              {
                "field": "mobileNumber",
                "message": "não deve estar em branco"
              },
              {
                "field": "name",
                "message": "não deve estar em branco"
              },
              {
                "field": "driverLicense",
                "message": "não deve estar em branco"
              }
            ]
          }
        ```
  </details>

  <details>
    <summary>Buscar todos os condutores</summary>

    - GET: http://localhost:8080/drivers
      - Request:
        ```bash
          curl -X GET 'localhost:8080/drivers'
        ```
      - Response 200:
        ```json
        [
          {
            "id": 1,
            "name": "Marcos Pedro Igor da Rosa",
            "driverLicense": "76279982890",
            "email": "marcos.pedro.darosa@gerj.com.br",
            "mobileNumber": "75982269616"
          },
          {
            "id": 3,
            "name": "Valentina Malu Melo",
            "driverLicense": "93694660327",
            "email": "valentinamalumelo@cenavip.com.br",
            "mobileNumber": "85992383628"
          }	
        ]
        ```
  </details>

  <details>
    <summary>Buscar um condutor</summary>

    - GET: http://localhost:8080/drivers/{id} *(id do condutor buscado)*
      - Request:
        ```bash
          curl -X GET 'localhost:8080/drivers/1'
        ```
      - Response 200:
        ```json
          {
            "id": 1,
            "name": "Marcos Pedro Igor da Rosa",
            "driverLicense": "76279982890",
            "email": "marcos.pedro.darosa@gerj.com.br",
            "mobileNumber": "75982269616"
          }
        ```
      - Response 404:
        ```json
          {
            "timestamp": "2023-09-22T01:14:08.092257160Z",
            "status": 404,
            "message": "Condutor não encontrado, id: 2",
            "path": "/drivers/2"
          }
        ```
  </details>  

  <details>
    <summary>Atualizar tipo de pagamento preferido</summary>

    - POST: http://localhost:8080/drivers/{id}/preferred-payment *(id do condutor a ser atualizado)*
      - Request:
        ```bash
          curl -X POST 'localhost:8080/drivers/1/preferred-payment' \
          -H 'Content-Type: application/json' \
          --data '{
            "preferredPaymentType":"PIX"
          }'
        ```
      - Response 200:
        ```json        
          {
            "id": 1,
            "name": "Breno Otávio da Silva",
            "driverLicense": "89786097264",
            "email": "livianinaassis@microlasersp.com.br",
            "mobileNumber": "18981407115",
            "preferredPaymentType": "PIX"
          }
        ```
      - Response 404:
        ```json
          {
            "timestamp": "2023-09-22T01:22:33.081665015Z",
            "status": 404,
            "message": "Condutor não encontrado, id: 2",
            "path": "/drivers/2"
          }
        ```
  </details>

  <details>
    <summary>Atualizar um condutor</summary>

    - PUT: http://localhost:8080/drivers/{id} *(id do condutor a ser atualizado)*
      - Request:
        ```bash
          curl -X PUT 'localhost:8080/drivers/1' \
          -H 'Content-Type: application/json' \
          --data '{
            "id": 4,
            "name": "Breno Otávio da Silva",
            "driverLicense": "89786097264",
            "email": "livianinaassis@microlasersp.com.br",
            "mobileNumber": "18981407115"
          }'
        ```
      - Response 200:
        ```json        
          {
            "id": 1,
            "name": "Breno Otávio da Silva",
            "driverLicense": "89786097264",
            "email": "livianinaassis@microlasersp.com.br",
            "mobileNumber": "18981407115"
          }
        ```
      - Response 404:
        ```json
          {
            "timestamp": "2023-09-22T01:22:33.081665015Z",
            "status": 404,
            "message": "Condutor não encontrado, id: 2",
            "path": "/drivers/2"
          }
        ```
  </details>

  <details>
    <summary>Deletar um condutor</summary>

    - DELETE: http://localhost:8080/drivers/{id} *(id do condutor a ser deletado)*
      - Request:
        ```bash
          curl -X DELETE 'localhost:8080/drivers/1'
        ```
      - Response 204:
        ```json
          {}
        ```
      - Response 404:
        ```json
          {
            "timestamp": "2023-09-22T01:22:33.081665015Z",
            "status": 404,
            "message": "Condutor não encontrado, id: 2",
            "path": "/drivers/2"
          }
        ```      
  </details>

- ### API de veículos:

  <details>
   <summary>Cadastrar um veículo</summary>

    - POST: http://localhost:8080/vehicles
        - Request:
          ```bash
            curl -X POST 'localhost:8080/vehicles' \
            -H 'Content-Type: application/json' \
            --data '{
              "brand":"chevrolet",
              "model":"onix",
              "color":"blue",
              "licensePlate": "AWS4528",
              "vehicleType": "CAR",
              "driverId":1
            }'
          ```
        - Response 201:
          ```json
            {
              "id": 5,
              "brand": "chevrolet",
              "model": "onix",
              "color": "blue",
              "licensePlate": "AWS4528",
              "vehicleType": "CAR"
            }
          ```
        - Response 400:
          ```json
            {
              "timestamp": "2023-10-19T20:58:40.102739302Z",
              "status": 400,
              "message": "ocorreu um ou mais erros de validação",
              "path": "/vehicles",
              "invalidParams": [
                {
                  "field": "model",
                  "message": "não deve estar em branco"
                },
                {
                  "field": "color",
                  "message": "não deve estar em branco"
                },
                {
                  "field": "licensePlate",
                  "message": "deve corresponder ao padrão (XXX8888)"
                },
                {
                  "field": "brand",
                  "message": "não deve estar em branco"
                },
                {
                  "field": "licensePlate",
                  "message": "não deve estar em branco"
                },
                {
                  "field": "vehicleType",
                  "message": "não deve ser nulo"
                }
              ]
            }
          ```        
  </details>

  <details>
    <summary>Buscar todos os veículos</summary>

    - GET: http://localhost:8080/vehicles
        - Request:
          ```bash
            curl -X GET 'localhost:8080/vehicles'
          ```
        - Response 200:
          ```json
          [
            {
              "id": 1,
              "brand": "fiat",
              "model": "marea",
              "color": "black",
              "licensePlate": "EFC7449",
              "vehicleType": "CAR"
            },
            {
              "id": 2,
              "brand": "volkswagen",
              "model": "gol",
              "color": "green",
              "licensePlate": "EYA1234",
              "vehicleType": "CAR"
            },
            {
              "id": 3,
              "brand": "volkswagen",
              "model": "gol",
              "color": "green",
              "licensePlate": "EYY1234",
              "vehicleType": "CAR"
            },
            {
              "id": 5,
              "brand": "chevrolet",
              "model": "onix",
              "color": "blue",
              "licensePlate": "AWS4528",
              "vehicleType": "CAR"
            }
          ]
          ```
  </details>

  <details>
    <summary>Buscar um veículo</summary>

    - GET: http://localhost:8080/vehicles/{id} *(id do veículo buscado)*
        - Request:
          ```bash
            curl -X GET 'localhost:8080/vehicles/1'
          ```
        - Response 200:
          ```json
            {
              "id": 1,
              "brand": "fiat",
              "model": "marea",
              "color": "black",
              "licensePlate": "EFC7449",
              "vehicleType": "CAR"
            }
          ```
        - Response 404:
          ```json
            {
              "timestamp": "2023-09-22T02:01:28.566949163Z",
              "status": 404,
              "message": "Veículo não encontrado, id: 20",
              "path": "/vehicles/20"
            }
          ```
  </details>  

  <details>
    <summary>Atualizar um veículo</summary>

    - PUT: http://localhost:8080/vehicles/{id} *(id do veículo a ser atualizado)*
        - Request:
          ```bash
            curl -X PUT 'localhost:8080/vehicles/1' \
            -H 'Content-Type: application/json' \
            --data '{
              "id":1,
              "brand":"fiat",
              "model":"marea",
              "color":"black",
              "licensePlate": "EFC7449",
              "vehicleType": "CAR"
            }'
          ```
        - Response 200:
          ```json        
            {
              "id": 1,
              "brand": "fiat",
              "model": "marea",
              "color": "black",
              "licensePlate": "EFC7449",
              "vehicleType": "CAR"
            }
          ```
        - Response 400:
          ```json
            {
              "timestamp": "2023-10-19T21:13:25.511734892Z",
              "status": 400,
              "message": "ocorreu um ou mais erros de validação",
              "path": "/vehicles/1",
              "invalidParams": [
                {
                  "field": "model",
                  "message": "não deve estar em branco"
                },
                {
                  "field": "brand",
                  "message": "não deve estar em branco"
                },
                {
                  "field": "licensePlate",
                  "message": "já existe um veiculo com essa placa"
                }
              ]
            }
          ```
  </details>

  <details>
    <summary>Deletar um veículo</summary>

    - DELETE: http://localhost:8080/vehicles/{id} *(id do veículo a ser deletado)*
        - Request:
          ```bash
            curl -X DELETE 'localhost:8080/vehicles/4'
          ```
        - Response 204:
          ```json
            {}
          ```
        - Response 404:
          ```json
            {
              "timestamp": "2023-09-22T01:44:37.350570675Z",
              "status": 404,
              "message": "Veículo não encontrado, id: 51",
              "path": "/vehicles"
            }
          ```      
  </details>

- ### API de Parquímetros

  <details>
    <summary>Cadastrar um parquímetro</summary>

    - POST: http://localhost:8080/parkingmeter
        - Request:
          ```bash
            curl -X POST 'localhost:8080/parkingmeter' \
            -H 'Content-Type: application/json' \
            --data '{
              "name":"Gabriel Martin Costa",
              "driverLicense":"49747790304",
              "email":"gabriel_costa@vegacon.com.br",
              "mobileNumber":"27988099520",
              "adressesIds":[1]
            }'
          ```
        - Response 201:
          ```json
            {
              "id": 5,
              "name": "Gabriel Martin Costa",
              "driverLicense": "49747790304",
              "email": "gabriel_costa@vegacon.com.br",
              "mobileNumber": "27988099520",
              "addresses":[{"id":1,"street":"Estrada da Madeira","number":"100","neighborhood":"Barragem","city":"Rio do Sul","state":"SC"}]
            }
          ```
        - Response 400:
          ```json
            {
              "timestamp": "2023-09-22T01:10:32.680793494Z",
              "status": 400,
              "message": "ocorreu um ou mais erros de validação",
              "path": "/drivers",
              "invalidParams": [
                {
                  "field": "email",
                  "message": "não deve estar em branco"
                },
                {
                  "field": "mobileNumber",
                  "message": "não deve estar em branco"
                },
                {
                  "field": "name",
                  "message": "não deve estar em branco"
                },
                {
                  "field": "driverLicense",
                  "message": "não deve estar em branco"
                }
              ]
            }
          ```
  </details>

  <details>
    <summary>Buscar todos os condutores</summary>

    - GET: http://localhost:8080/drivers
        - Request:
          ```bash
            curl -X GET 'localhost:8080/drivers'
          ```
        - Response 200:
          ```json
          [
            {
              "id": 1,
              "name": "Marcos Pedro Igor da Rosa",
              "driverLicense": "76279982890",
              "email": "marcos.pedro.darosa@gerj.com.br",
              "mobileNumber": "75982269616"
            },
            {
              "id": 3,
              "name": "Valentina Malu Melo",
              "driverLicense": "93694660327",
              "email": "valentinamalumelo@cenavip.com.br",
              "mobileNumber": "85992383628"
            }	
          ]
          ```
  </details>

  <details>
    <summary>Buscar um condutor</summary>

    - GET: http://localhost:8080/drivers/{id} *(id do condutor buscado)*
        - Request:
          ```bash
            curl -X GET 'localhost:8080/drivers/1'
          ```
        - Response 200:
          ```json
            {
              "id": 1,
              "name": "Marcos Pedro Igor da Rosa",
              "driverLicense": "76279982890",
              "email": "marcos.pedro.darosa@gerj.com.br",
              "mobileNumber": "75982269616"
            }
          ```
        - Response 404:
          ```json
            {
              "timestamp": "2023-09-22T01:14:08.092257160Z",
              "status": 404,
              "message": "Condutor não encontrado, id: 2",
              "path": "/drivers/2"
            }
          ```
  </details>  

  <details>
    <summary>Atualizar tipo de pagamento preferido</summary>

    - POST: http://localhost:8080/drivers/{id}/preferred-payment *(id do condutor a ser atualizado)*
        - Request:
          ```bash
            curl -X POST 'localhost:8080/drivers/1/preferred-payment' \
            -H 'Content-Type: application/json' \
            --data '{
              "preferredPaymentType":"PIX"
            }'
          ```
        - Response 200:
          ```json        
            {
              "id": 1,
              "name": "Breno Otávio da Silva",
              "driverLicense": "89786097264",
              "email": "livianinaassis@microlasersp.com.br",
              "mobileNumber": "18981407115",
              "preferredPaymentType": "PIX"
            }
          ```
        - Response 404:
          ```json
            {
              "timestamp": "2023-09-22T01:22:33.081665015Z",
              "status": 404,
              "message": "Condutor não encontrado, id: 2",
              "path": "/drivers/2"
            }
          ```
  </details>

  <details>
    <summary>Atualizar um condutor</summary>

    - PUT: http://localhost:8080/drivers/{id} *(id do condutor a ser atualizado)*
        - Request:
          ```bash
            curl -X PUT 'localhost:8080/drivers/1' \
            -H 'Content-Type: application/json' \
            --data '{
              "id": 4,
              "name": "Breno Otávio da Silva",
              "driverLicense": "89786097264",
              "email": "livianinaassis@microlasersp.com.br",
              "mobileNumber": "18981407115"
            }'
          ```
        - Response 200:
          ```json        
            {
              "id": 1,
              "name": "Breno Otávio da Silva",
              "driverLicense": "89786097264",
              "email": "livianinaassis@microlasersp.com.br",
              "mobileNumber": "18981407115"
            }
          ```
        - Response 404:
          ```json
            {
              "timestamp": "2023-09-22T01:22:33.081665015Z",
              "status": 404,
              "message": "Condutor não encontrado, id: 2",
              "path": "/drivers/2"
            }
          ```
  </details>

  <details>
    <summary>Deletar um condutor</summary>

    - DELETE: http://localhost:8080/drivers/{id} *(id do condutor a ser deletado)*
        - Request:
          ```bash
            curl -X DELETE 'localhost:8080/drivers/1'
          ```
        - Response 204:
          ```json
            {}
          ```
        - Response 404:
          ```json
            {
              "timestamp": "2023-09-22T01:22:33.081665015Z",
              "status": 404,
              "message": "Condutor não encontrado, id: 2",
              "path": "/drivers/2"
            }
          ```      
  </details>

- ### API de Tickets

  <details>
  <summary>Abrir um ticket com horário fixo</summary>

  - POST: http://localhost:8080/parkingmeter/{parkingMeterId}/ticket/open
      - Request:
        ```bash
          curl -X POST 'localhost:8080/parkingmeter/1/ticket/open' \
          -H 'Content-Type: application/json' \
          --data '{
            "driverId": 1,
            "vehicleId": 1,
            "parkingModality": "FIXED",
            "fixedHours": 2
          }'
        ```
      - Response 201:
        ```json
          {
            "id": 5,
            "name": "Gabriel Martin Costa",
            "driverLicense": "49747790304",
            "email": "gabriel_costa@vegacon.com.br",
            "mobileNumber": "27988099520",
            "addresses":[{"id":1,"street":"Estrada da Madeira","number":"100","neighborhood":"Barragem","city":"Rio do Sul","state":"SC"}]
          }
        ```
      - Response 400:
        ```json
          {
            "timestamp": "2023-09-22T01:10:32.680793494Z",
            "status": 400,
            "message": "ocorreu um ou mais erros de validação",
            "path": "/drivers",
            "invalidParams": [
              {
                "field": "email",
                "message": "não deve estar em branco"
              },
              {
                "field": "mobileNumber",
                "message": "não deve estar em branco"
              },
              {
                "field": "name",
                "message": "não deve estar em branco"
              },
              {
                "field": "driverLicense",
                "message": "não deve estar em branco"
              }
            ]
          }
        ```
  </details>

    <details>
      <summary>Abrir um ticket com horário livre</summary>
    
    - POST: http://localhost:8080/parkingmeter/{parkingMeterId}/ticket/open
        - Request:
          ```bash
            curl -X POST 'localhost:8080/parkingmeter/1/ticket/open' \
            -H 'Content-Type: application/json' \
            --data '{
              "driverId": 1,
              "vehicleId": 1,
              "parkingModality": "HOURLY"
            }'
          ```
        - Response 201:
          ```json
            {
              "id": 5,
              "name": "Gabriel Martin Costa",
              "driverLicense": "49747790304",
              "email": "gabriel_costa@vegacon.com.br",
              "mobileNumber": "27988099520",
              "addresses":[{"id":1,"street":"Estrada da Madeira","number":"100","neighborhood":"Barragem","city":"Rio do Sul","state":"SC"}]
            }
          ```
        - Response 400:
          ```json
            {
              "timestamp": "2023-09-22T01:10:32.680793494Z",
              "status": 400,
              "message": "ocorreu um ou mais erros de validação",
              "path": "/drivers",
              "invalidParams": [
                {
                  "field": "email",
                  "message": "não deve estar em branco"
                },
                {
                  "field": "mobileNumber",
                  "message": "não deve estar em branco"
                },
                {
                  "field": "name",
                  "message": "não deve estar em branco"
                },
                {
                  "field": "driverLicense",
                  "message": "não deve estar em branco"
                }
              ]
            }
          ```
  </details>

  <details>
  <summary>Fechar um ticket</summary>

  - POST: http://localhost:8080/parkingmeter/{parkingMeterId}/ticket/{ticketId}/close
      - Request:
        ```bash
          curl -X POST 'localhost:8080/parkingmeter/1/ticket/3/close' \
          -H 'Content-Type: application/json'
        ```
      - Response 201:
        ```json
          {
            "id": 5,
            "name": "Gabriel Martin Costa",
            "driverLicense": "49747790304",
            "email": "gabriel_costa@vegacon.com.br",
            "mobileNumber": "27988099520",
            "addresses":[{"id":1,"street":"Estrada da Madeira","number":"100","neighborhood":"Barragem","city":"Rio do Sul","state":"SC"}]
          }
        ```
      - Response 400:
        ```json
          {
            "timestamp": "2023-09-22T01:10:32.680793494Z",
            "status": 400,
            "message": "ocorreu um ou mais erros de validação",
            "path": "/drivers",
            "invalidParams": [
              {
                "field": "email",
                "message": "não deve estar em branco"
              },
              {
                "field": "mobileNumber",
                "message": "não deve estar em branco"
              },
              {
                "field": "name",
                "message": "não deve estar em branco"
              },
              {
                "field": "driverLicense",
                "message": "não deve estar em branco"
              }
            ]
          }
        ```
  </details>


