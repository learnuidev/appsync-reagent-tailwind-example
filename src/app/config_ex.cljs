(ns app.config-ex)

(defonce env {:aws {:region "us-east-1"
                    :userPoolId "example-user-pool-id"
                    :userPoolWebClientId "example-userpool-web-client-id"
                    :mandatorySignIn true
                    :graphqlEndpoint "https://your-api/graphql"
                    :authenticationType "AMAZON_COGNITO_USER_POOLS"}
              :auth {:username ""
                     :password ""}})
