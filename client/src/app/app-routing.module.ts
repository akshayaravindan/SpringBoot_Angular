import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { EmployeeListComponent } from './employee-list/employee-list.component';
import { EmployeeEditComponent } from './employee-edit/employee-edit.component';


const routes: Routes = [
  {path: '', redirectTo: '/employee-list', pathMatch: 'full'},
  {
    path: 'employee-list',
    component: EmployeeListComponent
  },
  {
    path: 'employee-add',
    component: EmployeeEditComponent
  },
  {
    path: 'employee-edit/:id',
    component: EmployeeEditComponent
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
