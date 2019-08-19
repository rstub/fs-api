# fs-api

The `fs-api` provides a REST API for the
[FatSecret](https://fatsecret.com) service. See the [architecture
documentation](docs/architecture.md) for an overview.

## Usage

In order to use `fs-api` one needs to
[register](https://platform.fatsecret.com/) to obtain OAuth 1.0
Credentials. These Consumer Key and Consumer Secret have to be provided
to `fs-api` as environment variables. Assuming a `bash` like shell:

```bash
$ export FATSECRET_CONSUMER_KEY=...
$ export FATSECRET_CONSUMER_SECRET=...
```

After that the application can be started from the top-level application
directory as

```bash
$ mvnw spring-boot:run
```

Alternativly you can split the tasks of building the (fat) JAR file from
executing it:

```bash
$ mvnw package
$ java -jar target/fs-api-0.0.1-SNAPSHOT.jar
```

For tests it is helpful to have a JSON pretty printer like `jq`
available:

```bash
$ curl --silent http://localhost:8080/api/v1/food?q=avocado | jq
[
  {
    "name": "Avocado",
    "url": "https://www.fatsecret.com/calories-nutrition/calavo/avocado",
    "type": "Brand",
    "id": 66325,
    "description": "Per 1/2 medium avocado - Calories: 130kcal | Fat: 12.00g | Carbs: 6.00g | Protein: 1.00g",
    "brandName": null
  },
  {
    "name": "Avocados",
    "url": "https://www.fatsecret.com/calories-nutrition/usda/avocados",
    "type": "Generic",
    "id": 35752,
    "description": "Per 100g - Calories: 160kcal | Fat: 14.66g | Carbs: 8.53g | Protein: 2.00g",
    "brandName": null
  },
  {
    "name": "Hass Avocado",
    "url": "https://www.fatsecret.com/calories-nutrition/trader-joes/hass-avocado",
    "type": "Brand",
    "id": 270988,
    "description": "Per 1/5 medium  - Calories: 50kcal | Fat: 4.50g | Carbs: 3.00g | Protein: 0.00g",
    "brandName": null
  },
  ...
]
$ curl --silent http://localhost:8080/api/v1/food/35752 | jq
{
  "name": "Avocados",
  "url": "https://www.fatsecret.com/calories-nutrition/usda/avocados",
  "type": "Generic",
  "id": 35752,
  "description": null,
  "brandName": "",
  "servings": [
    {
      "servingId": 32965,
      "servingDescription": "1 cup cubed",
      "servingUrl": "https://www.fatsecret.com/calories-nutrition/usda/avocados?portionid=32965&portionamount=1.000",
      "metricServingAmount": 150,
      "metricServingUnit": "g",
      "numberOfUnits": 1,
      "measurementDescription": "cup, cubes",
      "calories": 240,
      "carbohydrate": 12.8,
      "protein": 3,
      "fat": 21.99,
      "saturatedFat": 3.189,
      "polyunsaturatedFat": 2.724,
      "monounsaturatedFat": 14.698,
      "transFat": null,
      "cholesterol": 0,
      "sodium": 10,
      "potassium": 728,
      "fiber": 10,
      "sugar": 0.99,
      "vitaminA": 4,
      "vitaminC": 25,
      "calcium": 2,
      "iron": 5
    },
    ...
  ]
}
```
