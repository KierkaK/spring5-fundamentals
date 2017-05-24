package lab.aop.model;

import lab.aop.AopLog;

public class ApuBar implements Bar {

	public Squish sellSquish(Customer customer)  {
        if (customer.isBroke())
            throw new CustomerBrokenException();

        AopLog.append("Here is your Squish \n");
        return new Squish("Usual Squish");
    }
}