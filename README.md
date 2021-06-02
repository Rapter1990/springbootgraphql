# Spring Boot and Graphql

<img src="screenshots/springbootgraphql.PNG" alt="Main Information" width="800" height="400">

### 📖 Information

<ul style="list-style-type:disc">
  <li> <b>Graphql</b> is a query language to handle crud and other operations and is an alternative to REST for Web APIs. </li>
  <li> To handle with all opeations, a file extension of <b>.graphql</b> should be defined. </li>
  <li> All processes of <b>query</b> and <b>mutation</b> should becovered from <b>GraphQLQueryResolver</b> and <b>GraphQLMutationResolver</b> </li>
</ul>

### 🔨 Run the App

<b>1 )</b> Install GraphiQL from this website (<code>https://www.electronjs.org/apps/graphiql</code>)

<b>2 )</b> Check if all <b>maven dependencies</b> are installed by using this code <code> mvn clean install </code>

<b>3 )</b> Add all relevant graphql dependencies in pom.xml

<b>4 )</b> Define a <b>query endpoint URL</b> in the <b>GraphiQL</b> (<code>http://localhost:8081/graphql</code>)

<b>5 )</b> Run the <b>GraphiQL</b> after defining queries with its variable in its essential parts of the program

<b>6 )</b> Show its result in <b>h2</b> database after connecting with this url (<code>http://localhost:8081/h2-console/</code>)

### Table

<table style="border: 0px;">
  <tr>
     <td align="center" colspan="1"> <b>Entity</b> </td>
     <td align="center"> <b>Query Information</b> </td>
     <td align="center"> <b>Query</b> </td>
     <td align="center"> <b>Query Variables</b> </td>
  </tr>
  <tr>
     <td align="center" rowspan="6"> <b>Hospital</b> </td>
  </tr>
  <tr>
      <td align="center">Get All Hospital List</td>
      <td align="center">
           <code>
               {
                 hospitals {
                     id
                     name
                 }
               }
           </code>
      </td>
      <td align="center"> No Query Variable </td>
  </tr>
  <tr>
      <td align="center">Get Hospital by Id</td>
      <td align="center">
        <code>
            query{
              hospital(id:3) {
                id
                name
              }
            }
        </code>
      </td>
      <td align="center"> No Query Variable </td>
  </tr>
  <tr>
      <td align="center">Add New Hospital</td>
      <td align="center">
        <code>
            mutation newHospital($hospitalInput: HospitalInput!) {
              newHospital(hospital: $hospitalInput){
                name
              }
            }
        </code>      
      </td>
      <td align="center">
        <code>
            {
              "hospitalInput": {
                "name": "Hospital 6"
              }
            }
        </code>     
      </td>
  </tr>
  <tr>
      <td align="center">Update Hospital By Id</td>
      <td align="center">
        <code>
            mutation {
              updateHospital (
                id: 6
                name: "Hospital 6 Updated")
                {
                  id
                  name
                }
            }
        </code>
      </td>
      <td align="center"> No Query Variable </td>
  </tr>
  <tr>
        <td align="center">Delete Hospital by Id</td>
        <td align="center">
          <code>
              mutation {
                deleteHospital (id: 6)
              }
          </code>
        </td>
        <td align="center"> No Query Variable </td>
  </tr>
  <tr>
     <td align="center" rowspan="13"> <b>Department</b> </td>
  </tr>
  <tr>
     <td align="center"> Get All Department list with showing Hospital and Doctor </td>
     <td align="center">
        <code>
            {
              departments{
                id
                name
                doctors{
                  id
                  firstName
                  lastName
                  salary
                  position
                  age
                  birthday
                }
                hospital{
                  id
                  name
                }
              }
            }
        </code>
     </td>
     <td align="center"> No Query Variable </td>
  </tr>
  <tr>
     <td align="center"> Get All Department list with showing only Doctor</td>
     <td align="center">
        <code>
            {
              departments{
                id
                name
                doctors{
                  id
                  firstName
                  lastName
                  salary
                  age
                  birthday
                }
              }
            }
        </code>
     </td>
     <td align="center"> No Query Variable </td>
  </tr>
  <tr>
     <td align="center"> Get All Department list with showing only Hospital</td>
     <td align="center">
        <code>
            {
              departments{
                id
                name
                hospital{
                  id
                  name
                }
              }
            }
        </code>
     </td>
     <td align="center"> No Query Variable </td>
  </tr>
  <tr>
       <td align="center"> Get All Department list with showing its information</td>
       <td align="center">
          <code>
              {
                departments{
                  id
                  name
                }
              }
          </code>
       </td>
       <td align="center"> No Query Variable </td>
  </tr>
  <tr>
       <td align="center"> Get Department by Id with showing Hospital and Doctor </td>
       <td align="center">
          <code>
              query {
                department(id:6){
                  id
                  name
                  doctors{
                    id
                    firstName
                    lastName
                    salary
                    position
                    age
                    birthday
                  }
                  hospital{
                    id
                    name
                  }
                }
              }
          </code>
       </td>
       <td align="center"> No Query Variable </td>
  </tr>
  <tr>
        <td align="center"> Get Department by Id with showing Hospital </td>
        <td align="center">
            <code>
                query {
                  department(id:6){
                    id
                    name
                    hospital{
                      id
                      name
                    }
                  }
                }
            </code>
        </td>
        <td align="center"> No Query Variable </td>
  </tr>
  <tr>
        <td align="center"> Get Department by Id with showing Doctor </td>
        <td align="center">
              <code>
                  query {
                    department(id:6){
                      id
                      name
                      doctors{
                         id
                         firstName
                         lastName
                         salary
                         position
                         age
                         birthday
                      }
                    }
                  }
              </code>
        </td>
        <td align="center"> No Query Variable </td>
  </tr>
  <tr>
        <td align="center"> Get Department by Id with showing its information </td>
        <td align="center">
            <code>
                query {
                  department(id:6){
                    id
                    name
                  }
                }
            </code>
        </td>
        <td align="center"> No Query Variable </td>
  </tr>
  <tr>
        <td align="center"> Get Department by Id with showing its information </td>
        <td align="center">
          <code>
              query {
                department(id:6){
                  id
                  name
                }
              }
          </code>
        </td>
        <td align="center"> No Query Variable </td>
  </tr>
  <tr>
        <td align="center"> Add New Department </td>
        <td align="center">
            <code>
                mutation newDepartment($departmentInput: DepartmentInput!) {
                  newDepartment(department: $departmentInput){
                    name
                    hospital{
                      id
                    }
                  }
                }
            </code>
        </td>
        <td align="center">
            <code>
                {
                  "departmentInput": {
                    "name": "Department 10",
                    "hospitalId": 3
                  }
                }
            </code>
        </td>
  </tr>
  <tr>
      <td align="center"> Update Department By Id</td>
      <td align="center">
          <code>
              mutation updateDepartment($departmentInput: DepartmentInput!) {
                updateDepartment(id: 10,department: $departmentInput){
                  name
                }
              }
          </code>
      </td>
      <td align="center">
          <code>
              {
                "departmentInput": {
                  "name": "Department 10 Update",
                  "hospitalId": 3
                }
              }
          </code>
      </td>
  </tr>
  <tr>
    <td align="center"> Delete Department By Id</td>
    <td align="center">
        <code>
            mutation {
              deleteDepartment (id: 10)
            }
        </code>
    </td>
    <td align="center">
        No Query Variable
    </td>
  </tr>
  <tr>
     <td align="center" rowspan="14"> <b>Doctor</b> </td>
  </tr>
  <tr>
       <td align="center"> Get All Doctor list with showing Department and Hospital </td>
       <td align="center">
          <code>
              {
                doctors{
                  id
                  firstName
                  lastName
                  position
                  age
                  salary
                  birthday
                  department{
                    id
                    name
                  }
                  hospital{
                    id
                    name
                  }
                }
              }
          </code>
       </td>
       <td align="center"> No Query Variable </td>
  </tr>
  <tr>
        <td align="center"> Get All Doctor list with showing only Hospital</td>
        <td align="center">
            <code>
                {
                  doctors{
                    id
                    firstName
                    lastName
                    position
                    age
                    salary
                    birthday
                    hospital{
                      id
                      name
                    }
                  }
                }
            </code>
        </td>
        <td align="center"> No Query Variable </td>
  </tr>
  <tr>
      <td align="center"> Get All Doctor list with showing only Department</td>
      <td align="center">
          <code>
              {
                doctors{
                  id
                  firstName
                  lastName
                  position
                  age
                  salary
                  birthday
                  department{
                    id
                    name
                  }
                }
              }
          </code>
      </td>
      <td align="center"> No Query Variable </td>
  </tr>
  <tr>
        <td align="center"> Get All Doctor list with showing its information</td>
        <td align="center">
            <code>
                {
                  doctors{
                    id
                    firstName
                    lastName
                    position
                    age
                    salary
                    birthday
                  }
                }
            </code>
        </td>
        <td align="center"> No Query Variable </td>
  </tr>
  <tr>
          <td align="center"> Get Doctor by Id with showing Department and Hospital</td>
          <td align="center">
              <code>
                  {
                    doctor(id : 2){
                      id
                      firstName
                      lastName
                      position
                      age
                      salary
                      birthday
                      department{
                        id
                        name
                      }
                      hospital{
                        id
                        name
                      }
                    }
                  }
              </code>
          </td>
          <td align="center"> No Query Variable </td>
  </tr>
  <tr>
          <td align="center"> Get Doctor by Id with showing Hospital</td>
          <td align="center">
              <code>
                  {
                    doctor(id : 2){
                      id
                      firstName
                      lastName
                      position
                      age
                      salary
                      birthday
                      hospital{
                        id
                        name
                      }
                    }
                  }
              </code>
          </td>
          <td align="center"> No Query Variable </td>
  </tr>
  <tr>
          <td align="center"> Get Doctor by Id with showing Department </td>
          <td align="center">
              <code>
                  {
                    doctor(id : 2){
                      id
                      firstName
                      lastName
                      position
                      age
                      salary
                      birthday
                      department{
                        id
                        name
                      }
                    }
                  }
              </code>
          </td>
          <td align="center"> No Query Variable </td>
  </tr>
  <tr>
        <td align="center"> Get Doctor by Id with showing its information </td>
        <td align="center">
            <code>
                {
                  doctor(id : 2){
                    id
                    firstName
                    lastName
                    position
                    age
                    salary
                    birthday
                  }
                }
            </code>
        </td>
        <td align="center"> No Query Variable </td>
  </tr>
  <tr>
      <td align="center"> Get Doctor by Filter covering that Doctor has a surgeon, is greater than the age of 30 and its salary is greater than 12K </td>
      <td align="center">
          <code>
              {
                doctorsWithFilter(filter: {
                  position: {
                    operator: "eq",
                    value: "Surgeon"
                  },
                  age: {
                    operator: "gt"
                    value: "30"
                  }
                  salary: {
                    operator: "gt"
                    value: "12000"
                  }
                }) {
                  id
                  firstName
                  lastName
                  age
                  salary
                  position
                  birthday
                }
              }
          </code>
      </td>
      <td align="center"> No Query Variable </td>
  </tr>
  <tr>
        <td align="center"> Get Doctor by Filter covering that its birthdate ranges from "1980-01-01" to "1990-05-01" </td>
        <td align="center">
            <code>
                {
                  doctorsWithFilter(filter: {
                    birthday:{
                      operator: "birthdayDate" 
                      value: "1980-01-01,1990-05-01"
                    }
                  }) {
                    id
                    firstName
                    lastName
                    age
                    salary
                    position
                    birthday
                  }
                }
            </code>
        </td>
        <td align="center"> No Query Variable </td>
  </tr>
  <tr>
      <td align="center"> Add New Doctor </td>
      <td align="center">
          <code>
              mutation newDepartment($doctorInput: DoctorInput!) {
                newDoctor(doctor: $doctorInput){
                  firstName
                  lastName
                  position
                  age
                  salary
                  birthday
                  department{
                    id
                    name
                  }
                  hospital{
                    id
                    name
                  }
                }
              }
          </code>
      </td>
      <td align="center">
        <code>
            {
              "doctorInput": {
                "firstName": "FirstName üğişçöı",
                "lastName": "LastName ÜĞİŞÇÖI",
                "position": "Pediatrician",
                "salary": 5000,
                "age": 30,
                "birthday": "1990-08-02",
                "departmentId": 5,
                "hospitalId" : 2
              }
            }
        </code>     
      </td>
  </tr>
  <tr>
        <td align="center"> Update Doctor by Id </td>
        <td align="center">
            <code>
                mutation updateDoctor($doctorInput: DoctorInput!) {
                  updateDoctor(id: 8,doctor: $doctorInput){
                    firstName
                    lastName
                    position
                    age
                    salary
                    birthday
                    department{
                      name
                    }
                    hospital{
                      name
                    }
                  }
                }
            </code>
        </td>
        <td align="center">
          <code>
              {
                "doctorInput": {
                  "firstName": "FirstName üğişçöı Update",
                  "lastName": "LastName ÜĞİŞÇÖI Update",
                  "position": "Obstetrician",
                  "salary": 5000,
                  "age": 30,
                  "birthday": "1990-08-02",
                  "departmentId": 1,
                  "hospitalId" : 1
                }
              }
          </code>     
        </td>
  </tr>
  <tr>
      <td align="center"> Delete Doctor by Id </td>
      <td align="center">
          <code>
              mutation {
                deleteDoctor (id: 8)
              }
          </code>
      </td>
      <td align="center"> No Query Variable </td>
  </tr>
</table>

### Screenshots

<details>
<summary>Click here to show the screenshots of project</summary>
    <p> Figure 1 </p>
    <img src ="screenshots/1.PNG">
    <p> Figure 2 </p>
    <img src ="screenshots/2.PNG">
    <p> Figure 3 </p>
    <img src ="screenshots/3.PNG">
    <p> Figure 4 </p>
    <img src ="screenshots/4.PNG">
    <p> Figure 5 </p>
    <img src ="screenshots/5.PNG">
    <p> Figure 6 </p>
    <img src ="screenshots/6.PNG">
    <p> Figure 7 </p>
    <img src ="screenshots/7.PNG">
    <p> Figure 8 </p>
    <img src ="screenshots/8.PNG">
    <p> Figure 9 </p>
    <img src ="screenshots/9.PNG">
    <p> Figure 10 </p>
    <img src ="screenshots/10.PNG">
    <p> Figure 11 </p>
    <img src ="screenshots/11.PNG">
    <p> Figure 12 </p>
    <img src ="screenshots/12.PNG">
    <p> Figure 13 </p>
    <img src ="screenshots/13.PNG">
    <p> Figure 14 </p>
    <img src ="screenshots/14.PNG">
    <p> Figure 15 </p>
    <img src ="screenshots/15.PNG">
    <p> Figure 16 </p>
    <img src ="screenshots/16.PNG">
    <p> Figure 17 </p>
    <img src ="screenshots/17.PNG">
    <p> Figure 18 </p>
    <img src ="screenshots/18.PNG">
    <p> Figure 19 </p>
    <img src ="screenshots/19.PNG">
    <p> Figure 20 </p>
    <img src ="screenshots/20.PNG">
    <p> Figure 21 </p>
    <img src ="screenshots/21.PNG">
    <p> Figure 22 </p>
    <img src ="screenshots/22.PNG">
    <p> Figure 23 </p>
    <img src ="screenshots/23.PNG">
    <p> Figure 24 </p>
    <img src ="screenshots/24.PNG">
    <p> Figure 25 </p>
    <img src ="screenshots/25.PNG">
    <p> Figure 26 </p>
    <img src ="screenshots/26.PNG">
    <p> Figure 27 </p>
    <img src ="screenshots/27.PNG">
    <p> Figure 28 </p>
    <img src ="screenshots/28.PNG">
    <p> Figure 29 </p>
    <img src ="screenshots/29.PNG">
    <p> Figure 30 </p>
    <img src ="screenshots/30.PNG">
    <p> Figure 31 </p>
    <img src ="screenshots/31.PNG">
    <p> Figure 32 </p>
    <img src ="screenshots/32.PNG">
    <p> Figure 33 </p>
    <img src ="screenshots/33.PNG">
    <p> Figure 34 </p>
    <img src ="screenshots/34.PNG">
    <p> Figure 35 </p>
    <img src ="screenshots/35.PNG">        
</details>