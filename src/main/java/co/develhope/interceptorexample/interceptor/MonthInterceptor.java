package co.develhope.interceptorexample.interceptor;

import co.develhope.interceptorexample.entities.Month;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Component
public class MonthInterceptor implements HandlerInterceptor {


    public List<Month> months = new ArrayList<>(Arrays.asList(
            new Month(1,"Gennaio","January","Januar"),
            new Month(2,"Febbario","February","Februar"),
            new Month(3,"Marzo","March","Marsch"),
            new Month(4,"Aprile","April","April"),
            new Month(5,"Maggio","May","Kann"),
            new Month(6,"Giugno","June","Juni")
    ));

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String numberMonthInString = request.getHeader("monthNumber");
        if(numberMonthInString == null || numberMonthInString.isEmpty()){
            response.setStatus(400);
            return true;
        }
        int monthNumber = Integer.parseInt(numberMonthInString);
        Optional<Month> month = months.stream().filter(oneMonth ->{
            return  oneMonth.getMonthNumber() == monthNumber;
        }).findAny();
        if(month.isPresent()){
            request.setAttribute("MonthInterceptor-month",month.get());
        }
        if(month.isEmpty()){
            request.setAttribute("MonthInterceptor-month",new Month(0,"nope","nope","nope"));
        }
        response.setStatus(200);
        return true;
    }
}
