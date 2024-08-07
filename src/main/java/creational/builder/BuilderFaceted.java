package creational.builder;

class PersonFaceted
{
    // address
    public String streetAddress, postcode, city;

    // employment
    public String companyName, position;
    public int annualIncome;

    @Override
    public String toString()
    {
        return "Person{" +
                "streetAddress='" + streetAddress + '\'' +
                ", postcode='" + postcode + '\'' +
                ", city='" + city + '\'' +
                ", companyName='" + companyName + '\'' +
                ", position='" + position + '\'' +
                ", annualIncome=" + annualIncome +
                '}';
    }
}

// builder facade
class PersonBuilderFaceted
{
    // the object we're going to build
    protected PersonFaceted person = new PersonFaceted(); // reference!

    public PersonJobBuilder works()
    {
        return new PersonJobBuilder(person);
    }

    public PersonAddressBuilder lives()
    {
        return new PersonAddressBuilder(person);
    }

    public PersonFaceted build()
    {
        return person;
    }
}

class PersonAddressBuilder extends PersonBuilderFaceted
{
    public PersonAddressBuilder(PersonFaceted person)
    {
        this.person = person;
    }

    public PersonAddressBuilder at(String streetAddress)
    {
        person.streetAddress = streetAddress;
        return this;
    }

    public PersonAddressBuilder withPostcode(String postcode)
    {
        person.postcode = postcode;
        return this;
    }

    public PersonAddressBuilder in(String city)
    {
        person.city = city;
        return this;
    }
}

class PersonJobBuilder extends PersonBuilderFaceted
{
    public PersonJobBuilder(PersonFaceted person)
    {
        this.person = person;
    }

    public PersonJobBuilder at(String companyName)
    {
        person.companyName = companyName;
        return this;
    }

    public PersonJobBuilder asA(String position)
    {
        person.position = position;
        return this;
    }

    public PersonJobBuilder earning(int annualIncome)
    {
        person.annualIncome = annualIncome;
        return this;
    }
}

public class BuilderFaceted {
    public static void main(String[] args) {
        PersonBuilderFaceted pb = new PersonBuilderFaceted();
        PersonFaceted person = pb
                .lives()
                .at("123 London Road")
                .in("London")
                .withPostcode("SW12BC")
                .works()
                .at("Fabrikam")
                .asA("Engineer")
                .earning(123000)
                .build();
        System.out.println(person);
    }
}
