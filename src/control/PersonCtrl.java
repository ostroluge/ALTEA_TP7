package control;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import model.dao.PersonDAO;
import model.dto.Person;

@ManagedBean (name="personCtrl")
@SessionScoped
public class PersonCtrl {

	private PersonDAO pDao = new PersonDAO();
	private List<Person> persons;
	private Person newPerson = new Person();
	private Long idPersonSelected;
	private Person mEditPerson;

	public List<Person> getPersons() {
		if (persons == null) {
			persons = pDao.selectAll();
		}
		return persons;
	}

	public String createPerson() {
		pDao.insert(newPerson);
		newPerson = new Person();
		persons.clear();
		persons.addAll(pDao.selectAll());
		return "list";
	}

	public String deletePerson() {
		Person p = pDao.getPerson(idPersonSelected);
		pDao.delete(p);
		persons.clear();
		persons.addAll(pDao.selectAll());
		return "list";
	}

	public String editPerson() {
		mEditPerson = pDao.getPerson(idPersonSelected);
		return "edit";
	}

	public String updatePerson() {
		pDao.update(mEditPerson);
		persons.clear();
		persons.addAll(pDao.selectAll());
		return "list";
	}

	public Person getNewPerson() {
		return newPerson;
	}

	public void setNewPerson(Person newPerson) {
		this.newPerson = newPerson;
	}

	public Long getIdPersonSelected() {
		return idPersonSelected;
	}

	public void setIdPersonSelected(Long idPersonSelected) {
		this.idPersonSelected = idPersonSelected;
	}

	public Person getEditPerson() {
		return mEditPerson;
	}

	public void setEditPerson(Person editPerson) {
		this.mEditPerson = editPerson;
	}
}
