package lab.model.simple;

import lab.aop.AopLog;
import lab.aop.CustomerBrokenException;
import lab.model.Bar;
import lab.model.Person;

public class ApuBar implements Bar {

    @Override
	public Squishee sellSquishee(Person customer)  {
        if (customer.isBroke())
            throw new CustomerBrokenException();

        AopLog.append("Here is your Squishee \n");
        return new Squishee("Usual Squishee");
    }
}