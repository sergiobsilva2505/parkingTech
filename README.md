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
            "mobileNumber":	"27988099520"
          }'
        ```
      - Response 201:
        ```json
          {
            "id": 5,
            "name": "Gabriel Martin Costa",
            "driverLicense": "49747790304",
            "email": "gabriel_costa@vegacon.com.br",
            "mobileNumber": "27988099520"
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
            "state": "SC",
            "driverId": 5
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
            "state": "SC",
            "driver": {
              "id": 5,
              "name": "Gabriel Martin Costa",
              "driverLicense": "49747790304",
              "email": "gabriel_costa@vegacon.com.br",
              "mobileNumber": "27988099520"
            }
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
            "state": "RS",
            "driver": {
              "id": 4,
              "name": "Breno Otávio da Silva",
              "driverLicense": "07386134995",
              "email": "breno_dasilva@mula.com.br",
              "mobileNumber": "66989531191"
            }
          },
          {
            "id": 4,
            "street": "Avenida Mendonça Júnior 126",
            "number": "762",
            "neighborhood": "Central",
            "city": "Macapá",
            "state": "AP",
            "driver": {
              "id": 3,
              "name": "Valentina Malu Melo",
              "driverLicense": "93694660327",
              "email": "valentinamalumelo@cenavip.com.br",
              "mobileNumber": "85992383628"
            }
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
            "state": "RS",
            "driver": {
              "id": 4,
              "name": "Breno Otávio da Silva",
              "driverLicense": "07386134995",
              "email": "breno_dasilva@mula.com.br",
              "mobileNumber": "66989531191"
            }
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
  

