import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LobbyComponent } from './lobby/lobby.component';
import { GameCreateComponent } from './game-create/game-create.component';
import { WaitingRoomComponent } from './waiting-room/waiting-room.component';
import {DemoMaterialModule} from './material-module';

@NgModule({
  declarations: [
    AppComponent,
    LobbyComponent,
    GameCreateComponent,
    WaitingRoomComponent,
    DemoMaterialModule
  ],
  imports: [
    BrowserModule,
    FormsModule,
    AppRoutingModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
