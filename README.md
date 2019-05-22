
# EPL441Clinic

Final Project for EPL441 - Advanced Software Engineering

## Overview
We used OSGi R7 specification to implement this application for a Clinic's Management. We used bnd and Eclipse's Bndtools plugin with OSGi annotations as the OSGi implementation([bnd workspace model](https://bndtools.org/concepts.html)). Each OSGi component (annotated with the @Component annotation) is located in a private package of an OSGi bundle and implements the corresponding interface located in an exported package of the OSGi bundle.
Services and View Components are implemented as OSGi components. Bndtools bootstraps our bnd workspace with a gradle script used to build, run, test and generate javadocs for our OSGi bundles. We have three OSGi application bundles(meaning they are executable), namely Clinic main bundle for the application, Migration for migrating the database schema and Seed for seeding the database with mock records. We have a model package to describe the entities of the database where each entity corresponds to a java class, each model has a corresponding Service component for CRUD operations to the DB. Each main service has a corresponding JUnit Test Case that asserts the correctness of CRUD operations to the databse. For the Database we used MySQL hosted through a Docker container.

## For Development

### Install Necessary Eclipse Plug-Ins.
1. Installing Eclipse Plug-In Development Environment(Necessary for developing OSGi Bundles): 
```
Help-> Install New Software... -> Work with: --All Available Sites-- --> Select Eclipse Plug-In Development Environment --> Install
```
2. Installing Bndtools(Necessary for using bnd, tool that makes OSGi development easier, and using the [bnd workspace model](https://bndtools.org/concepts.html).
```
Help-> Install New Software... -> Work with: --All Available Sites-- --> Select Bndtools and all subplug-ins --> Install
```

### Install Gradle
[Gradle](https://gradle.org/) is the tool that we will use to build the project(instead of ant/maven) as it is the requirement for the Project to have a build script.


### Setting Up Workspace

1. Clone the repository by running.

```bash
git clone https://github.com/npafitis/EPL441Clinic.git <path-to-your-workspace-name>
```

2. The repository you just cloned will be the workspace that you are going to use in Eclipse. Set the Eclipse workspace to that repository. Now, in your package explorer you should only see a cnf folder

### Creating an OSGi Bundle

1. New Project (Wizard).

2. Under Bndtools select Bnd OSGi Project, then Next.

3. Select Component Project.

4. Create Project.

### Creating an OSGi Executable Bundle

1. New Project (Wizard).

2. Under Bndtools select Bnd OSGi Project, then Next.

3. Select Application Project.

4. Create Project.

### Setting Up MySQL DataBase

Database has been set up using docker to avoid any installation/configuration problems across different team members. A __docker-compose.yml__ file with a MySQL image.

1. Install [Docker](https://www.docker.com/)

2. Run in terminal

```bash
docker-compose up
```

The database must be running now.

Optional:

3.  Install [MySQL Workbench](https://www.mysql.com/products/workbench/) for IDE interface to MySQL.

4. Open MySQL Workbench.

5. Create a new Connection with the following settings

```
Host: 0.0.0.0
port: 33061
username: homestead
```

### Consuming Services
Implementation classes of Services are not exported. That means that they're not public.
What you do instead is a reference to the public interface, example:
```java
@Ref
private UserService service;
```
and it will be automatically instantiated.
You have to include the model.services.implementation package in your Run Requirements in bndrun.

### Migrating Database(Creating DataBase Schema)
For *nix Systems run the following
```bash
sudo ./gradlew run.cy.ac.ucy.epl441.migration
```

For Windows Systems run __gradlew.bat__ instead with the same arguments.

Password is: __secret__

For seeding the database with mockup records:
```bash
sudo ./gradlew run.cy.ac.ucy.epl441.seed
```

## Database Schema

![Schema](./EPL441ERD.png)

## Deployment


### Building Project Applications
To build all Application Project in the workspace run

```bash
./gradlew
```

### Resolve Project Applications
```bash
./gradlew resolve
```

### Run JUnit tests
```bash
./gradlew test
```

### Running Clinic application
```bash
./gradlew run.cy.ac.ucy.epl441.clinic
```

### Generate javadocs
```bash
./gradlew javadoc
```
