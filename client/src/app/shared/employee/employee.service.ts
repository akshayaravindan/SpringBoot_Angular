import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class EmployeeService {
  public API = '//localhost:8080';
  public EMPLOYEE_API = this.API + '/employees';

  constructor(private http: HttpClient) { }

  getAll(): Observable<any> {
    return this.http.get('//localhost:8080/employees');
  }

  get(id: string) {
    return this.http.get(this.EMPLOYEE_API + '/' + id);
  }

  save(employee: any): Observable<any> {
    let result: Observable<any>;
    if (employee.href) {
      result = this.http.put(employee.href, employee);
    }
    else {
      result = this.http.post(this.EMPLOYEE_API, employee);
    }

    return result;
  }

  remove(href: string) {
    return this.http.delete(href);
  }
}
