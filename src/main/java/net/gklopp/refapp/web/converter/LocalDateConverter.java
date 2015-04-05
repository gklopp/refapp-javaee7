package net.gklopp.refapp.web.converter;


import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Locale;

/**
 * JSF Converter for new DateTime API.
 */
@FacesConverter("localDateConverter")
public class LocalDateConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent uiComponent, String dateAsString) {

        if ((dateAsString == null) || (dateAsString.isEmpty())) {
            return null;
        }

        try {
            DateTimeFormatter dateTimeFormatter = getDateTimeFormatter(facesContext);
            return LocalDate.parse(dateAsString, dateTimeFormatter);
        } catch (DateTimeParseException dateTimeParseException) {

            FacesMessage dateConversionErrorMessage = getDateConversionErrorMessage(facesContext);
            throw new ConverterException(dateConversionErrorMessage);
        }
    }

    private FacesMessage getDateConversionErrorMessage(FacesContext facesContext) throws ConverterException {

        String errorMessage = facesContext.getApplication().evaluateExpressionGet(facesContext,
                "#{validationMessages['general.date.notValid']}", String.class);
        return new FacesMessage(FacesMessage.SEVERITY_ERROR, errorMessage, errorMessage);
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object date) {

        String dateAsString = "";

        if (date instanceof LocalDate) {
            LocalDate localDate = (LocalDate) date;
            DateTimeFormatter dateTimeFormatter = getDateTimeFormatter(facesContext);
            dateAsString =  dateTimeFormatter.format(localDate);
        }

        return dateAsString;
    }

    private DateTimeFormatter getDateTimeFormatter(FacesContext facesContext) {

        Locale locale = facesContext.getViewRoot().getLocale();

        if (locale.equals(Locale.FRENCH)) {
            return DateTimeFormatter.ofPattern("dd/MM/yyyy");
        } else {
            return DateTimeFormatter.ofPattern("yyyy/MM/dd");
        }
    }
}
