package com.gtu14.bean;

import javax.faces.bean.ManagedBean;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.gtu14.entity.University;

// You must annotate the converter as a managed bean, if you want to inject
// anything into it, like your persistence unit for example.
@ManagedBean(name = "universityConverterBean") 
@FacesConverter(value = "categoryConverter")
public class UniversityConverter implements Converter {

    @PersistenceContext
    private transient EntityManager em;  

    @Override
    public Object getAsObject(FacesContext ctx, UIComponent component,
            String value) {
      // This will return the actual object representation
      // of your Category using the value (in your case 52) 
      // returned from the client side
      return em.find(University.class, value); 
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object o) {
        //This will return view-friendly output for the dropdown menu
        return ((University) o).toString(); 
    }
}