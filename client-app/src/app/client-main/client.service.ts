import { Injectable } from '@angular/core';
import { Client } from './client';
import { HttpClient, HttpHeaders  } from '@angular/common/http';
import { map, catchError } from 'rxjs/operators';
import { Observable, throwError } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ClientService {
  private baseUrl = 'http://localhost:8080/api/clients';

  constructor(private http: HttpClient) { }

  getClients(): Observable<any> {
    return this.http.get(this.baseUrl);
  }

  create(client:Client) : Observable<Client>{
    const headers = new HttpHeaders({ 'Content-Type': 'application/json' });
    return this.http.post(this.baseUrl, client, { headers }).pipe(
      map((response: any) => response.client as Client),
      catchError((error) => {
        console.error('Error creating the client:', error);
        return throwError('Error creating the client');
      })
    );  
  }

  searchClientsByKey(sharedKey: string): Observable<any> {
    const url = `${this.baseUrl}/${sharedKey}`;
    return this.http.get(url).pipe(
      catchError((error) => {
        console.error('Error searching clients:', error);
        return throwError('Error searching clients');
      })
    );
  }

  
}
