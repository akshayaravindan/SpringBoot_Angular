import { Component, OnInit } from '@angular/core';
import { EmployeeService } from '../shared/employee/employee.service'
// import { FormControl } from '@angular/forms'

@Component({
  selector: 'app-employee-list',
  templateUrl: './employee-list.component.html',
  styleUrls: ['./employee-list.component.css']
})
export class EmployeeListComponent implements OnInit {
  employees: Array<any>;

  // results: any[] = [];
  // queryField: FormControl = new FormControl();
  constructor(private EmployeeService: EmployeeService) { }

  ngOnInit() {
    this.EmployeeService.getAll().subscribe((data) => {
      this.employees = data;
    })

    // this.queryField.valueChanges.subscribe(result => console.log(result))
  }

}
