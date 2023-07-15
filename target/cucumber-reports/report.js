$(document).ready(function() {var formatter = new CucumberHTML.DOMFormatter($('.cucumber-report'));formatter.uri("file:src/test/resources/functionalTests/FunctionalTests.feature");
formatter.feature({
  "name": "End to End Tests for Registration and Successful login flow",
  "description": "Description: These tests are for creating and login account with correct credentials.",
  "keyword": "Feature"
});
formatter.scenario({
  "name": "Registration flow",
  "description": "",
  "keyword": "Scenario",
  "tags": [
    {
      "name": "@TC001"
    },
    {
      "name": "@Registration"
    }
  ]
});
formatter.step({
  "name": "Define URI and base path as pre-requisites",
  "keyword": "Given "
});
formatter.match({
  "location": "stepDefinitions.Steps.define_URI_and_base_path_as_prerequisite()"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "Define headers for the post API call",
  "keyword": "And "
});
formatter.match({
  "location": "stepDefinitions.Steps.define_headers_for_the_post_API_call()"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "Create account through post api call",
  "keyword": "When "
});
formatter.match({
  "location": "stepDefinitions.Steps.create_account_through_post_api_call()"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "Validate the create account outcomes",
  "keyword": "Then "
});
formatter.match({
  "location": "stepDefinitions.Steps.validate_the_create_account_outcomes()"
});
