# RiskProjectBack

## Static analysis with SonarQube

Analyzing a Maven project consists of running a Maven sonar command from the directory that holds the main project pom.xml

You can then browse your localhost where your sonar server is running to access the dashboard

`mvn clean install`
`mvn sonar:sonar`

You can configure the satic analysis parameters in the pom.xml

## Unit testing with JUnit

The unit tests can be found in the test folder

## Unit testing coverage with Cobertura

Analyzing a Maven project consists of running a Maven cobertura command from the directory that holds the main project pom.xml

You can then browse the target repository to find the cobertura report that was generated

`mvn cobertura:cobertura`

You can configure the check phase in the pom.xml

`<configuration>
    <check>
        <haltOnFailure>true</haltOnFailure>
        <branchRate>75</branchRate>
        <lineRate>85</lineRate>
        <totalBranchRate>75</totalBranchRate>
        <totalLineRate>85</totalLineRate>
        <packageLineRate>75</packageLineRate>
        <packageBranchRate>85</packageBranchRate>
        <regexes>
            <regex>
                <pattern>com.baeldung.algorithms.dijkstra.*</pattern>
                <branchRate>60</branchRate>
                <lineRate>50</lineRate>
            </regex>
        </regexes>
    </check>
</configuration>`


## Javadoc

The javadoc has been generated inside the Javadoc folder