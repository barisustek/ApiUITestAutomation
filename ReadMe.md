## Trello UI and API Test Automation Study

UI test automation is developed with Selenium framework. POM design pattern has been followed while developing the project. Also, Page Object Factory is used for storing web elements.

API test automation is developed with Rest Assured framework. Model classes are created for API.

For expanding the test suit, I use BDD frameworks. Test scenarios were prepared with the BDD approach. I used Cucumber framework for this. There are 5 API and 4 UI test scenarios. In UI scenarios, 2 of them consist of both UI actions and API calls.

For reporting, I use extent report. The report of the last performed test is in the test-output folder.

**According to the credentials warning in Assignment, the user information was not added to the project:**

Key and Token are required to run the tests. This information needs to be added to config.properties before tests run.

Also, user/pass information for UI tests is available in UiTestCases.feature. This information must also be entered before tests run. **(username_info, password_info)**

**Programming language :**

* Java: JDK16

**Frameworks that using in the project :**

* Selenium: 4.4.0
* Cucumber: 6.8.1
* Extent Reports: 4.1.7
* maven : 4.0.0
* Rest Assured: 5.1.1
