# CinemaReservations
Project for Warsaw Military Academy of Technology about using voice recognition capabilities of Evolution Voxeo portal to create cinema tickets reservation system. 

# Development tips
## Database Character Set
Before populating database using `classpath:db/populate.sql` script, either log in to mysql using `mysql --default-character-set=utf8` command or setup character set using .cnf file:
```
[mysql]
default-character-set=utf8
```
## Using LocalDateTime etc
JPA 2.1 does not support new Java 8 Date/Time API out of the box. Special converters need to be implemented:
```Java
@Converter(autoApply = true)
public class LocalDateTimeAttributeConverter implements AttributeConverter<LocalDateTime, Timestamp> {

    @Override
    public Timestamp convertToDatabaseColumn(LocalDateTime locDateTime) {
        return (locDateTime == null ? null : Timestamp.valueOf(locDateTime));
    }

    @Override
    public LocalDateTime convertToEntityAttribute(Timestamp sqlTimestamp) {
        return (sqlTimestamp == null ? null : sqlTimestamp.toLocalDateTime());
    }
}
```
## MVC Integration Test - JsonPath
Whenever integration tests for controllers are made, it might be useful to validate returned JSON. JsonPath is used for this. Check [this](https://github.com/jayway/JsonPath) for official documentation and [this](http://jsonpath.herokuapp.com/) for online jsonpath checker.
Example:
```Java
mockMvc.perform(get("/customers")).andExpect(status().isOk())
               .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
               .andExpect(jsonPath("$").isArray())
               .andExpect(jsonPath("$.*.id").exists())
               .andExpect(jsonPath("$.*.code").exists());
```
## Returning object from parameters when mocking
When mocking using Mockito it is possible to return the same object that was passed as a parameter to a mocked method:
```Java
when(customerRepository.save(any(Customer.class))).then(AdditionalAnswers.returnsFirstArg());
```
`AdditionalAnswers` is a pretty handy class when mocking.
## Fetching data from backend to VXML
VXML allows to fetch XML data from backend. It can then be retrieving using JS DOM methods. Fetching:
```Xml
<data name="jokeResponse" src="joke" />
<assign name="document.jokeResponse" expr="jokeResponse.documentElement" />
<prompt>Dzień dobry. <value expr="dialogContext.getData(jokeResponse, 'joke')"/></prompt>
```
JS DOM usage:
```JavaScript
function getData(doc, tagName) {
    return doc.getElementsByTagName(tagName).item(0).firstChild.data;
}
```