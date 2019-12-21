# RiskProjectBack

## Static analysis with SonarQube

Analyzing a Maven project consists of running a Maven sonar command from the directory that holds the main project pom.xml

You can then browse your localhost where your sonar server is running to access the dashboard

`mvn clean install`
`mvn sonar:sonar`

You can configure the satic analysis parameters in the pom.xml

## Auto-generated unit tests with JUnit and EvoSuit

EvoSuite is a tool that automatically generates unit tests for Java software. EvoSuite uses an evolutionary algorithm to generate JUnit tests. The EvoSuite Maven plugin provides the following generate goal used to generate test cases with EvoSuite. Tests will be generated for all classes in all submodules. EvoSuite creates the tests in the `.evosuite` folder. 

`mvn compile evosuite:generate`

## Unit testing coverage with Cobertura

Analyzing a Maven project consists of running a Maven cobertura command from the directory that holds the main project pom.xml

You can then browse the target repository to find the cobertura report that was generated

`mvn cobertura:cobertura`

You can configure the check phase in the pom.xml

`<configuration>
    <check>
        [...]
    </check>
</configuration>`


## Javadoc

The javadoc has been generated inside the Javadoc folder