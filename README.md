# DBCP2 Example

## Azure Resources

```shell
export AZ_RESOURCE_GROUP=dbcp2-example
export AZ_DATABASE_SERVER_NAME=nick-dbcp2-example
export AZ_DATABASE_NAME=demo
export AZ_LOCATION=eastus
export DB_ADMIN_NAME=nick
export DB_ADMIN_PASSWORD=<CHANGE_ME>
export APPSERVICE_PLAN=dbcp2-example-app-plan
export APPSERVICE_NAME=dbcp2-example-app
```

```shell
az group create --name $AZ_RESOURCE_GROUP --location $AZ_LOCATION
```

```shell
az postgres flexible-server create \
    --resource-group $AZ_RESOURCE_GROUP \
    --name $AZ_DATABASE_SERVER_NAME \
    --location $AZ_LOCATION \
    --storage-size 32 \
    --version 12
    --admin-user $DB_ADMIN_NAME \
    --admin-password $DB_ADMIN_PASSWORD \
    --public-network-access 0.0.0.0 \
    --output tsv
```

```shell
az postgres flexible-server db create \
    --resource-group $AZ_RESOURCE_GROUP \
    --server-name $AZ_DATABASE_SERVER_NAME \
    --database-name $AZ_DATABASE_NAME
```

```shell
az appservice plan create \
    --resource-group $AZ_RESOURCE_GROUP \
    --name $APPSERVICE_PLAN \
    --location $AZ_LOCATION \
    --sku P1v2 \
    --is-linux
```

```shell
az webapp create \
    --resource-group $AZ_RESOURCE_GROUP \
    --name $APPSERVICE_NAME \
    --plan $APPSERVICE_PLAN \
    --runtime "Java:8-jre8"
```

```shell
az webapp connection create postgres-flexible \
    --resource-group $AZ_RESOURCE_GROUP \
    --name $APPSERVICE_NAME \
    --target-resource-group $AZ_RESOURCE_GROUP \
    --server $AZ_DATABASE_SERVER_NAME \
    --database $AZ_DATABASE_NAME \
    --client-type java \
    --system-identity
```

## Update Application properties

Edit the application.properties file with the values from above.

```
DatabaseConfigEmbedUrl
DatabaseConfigEmbedUsername
```

## Build

```shell
mvn package
```

## Deploy

```shell
mvn com.microsoft.azure:azure-webapp-maven-plugin:2.6.1:deploy
```
