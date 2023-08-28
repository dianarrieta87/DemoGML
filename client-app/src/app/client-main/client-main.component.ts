import { Component, OnInit } from '@angular/core';
import { ClientService } from './client.service';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { ClientFormComponent } from '../client-form/client-form.component';
import swal from 'sweetalert2';

@Component({
  selector: 'app-client-main',
  templateUrl: './client-main.component.html',
  styleUrls: ['./client-main.component.css']
})
export class ClientMainComponent implements OnInit {
  clients: any[] = [];
  searchKey: string = '';

  constructor(private clientService: ClientService, private modalService: NgbModal) { }

  ngOnInit(): void {
    this.clientService.getClients().subscribe(
      (data) => {
        this.clients = data;
      },
      (error) => {
        console.error('Error with clients:', error);
      }
    );
  }

  openFormModal() {
    const modalRef = this.modalService.open(ClientFormComponent);
  }

  searchClients() {
    console.log(this.searchKey);
    this.clientService.searchClientsByKey(this.searchKey).subscribe(
      (data) => {        
        this.clients = [];
        this.clients.push(data);
      },
      (error) => {
        console.error('Error searching clients:', error);
        swal.fire('Client not found', error.message, 'error');
      }
    );
  }
}
