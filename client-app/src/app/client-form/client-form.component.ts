import { Component, Input } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Client } from '../client-main/client';
import { ClientService } from '../client-main/client.service';
import swal from 'sweetalert2';

@Component({
  selector: 'app-client-form',
  templateUrl: './client-form.component.html',
  styleUrls: ['./client-form.component.css']
})
export class ClientFormComponent {

  client: Client = new Client;
  errors: string[] = [];

  constructor(public modal: NgbActiveModal, 
    private clientService: ClientService) { }

  saveClient() {
    this.clientService.create(this.client)
      .subscribe(
        cliente => {
          swal.fire('New Client', `The client ${this.client.sharedKey} has been created successfully`, 'success');
          this.modal.close();
        },
        err => {
          this.errors = err.error.errors as string[];
          console.error('error saving the client: ' + err.status, err);
          swal.fire('Error creating client', err.error.message, 'error');
        }
      );
  }
}
