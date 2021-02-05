import {AfterViewInit, Component, OnInit, OnDestroy} from '@angular/core';
import { LoginService } from 'services/login.service';
import { SettingsService } from '../services/settings.service';

@Component({
  selector: 'app-sidebar',
  templateUrl: './sidebar.component.html',
  styleUrls: ['./sidebar.component.css']
})

export class SidebarComponent implements OnInit, AfterViewInit, OnDestroy {
  public color: string;
  public menuItems: object;
  public activeFontColor: string;
  public normalFontColor: string;
  public dividerBgColor: string;
    ROUTES = [
    { path: '/dashboard', title: 'Home', icon: 'home', children: null ,display:true},
    {path:'profil',title:'Profile',icon:'group',children:null,display:this.isAuthenticated()},
   
    {
      path: '#member-list',id: 'member-list' ,title: 'Members', icon: 'group', display:true,children: [
        { path: 'members', title: 'List of Members' ,display:true},
     
        { path: 'members/events', title: 'Event Management',display:this.isAuthenticated()},
  
      ],
    },
      { path: 'publications', title: 'Publication', icon: 'bookmarks',display:true, children: null },
    { path: 'tools', title: 'Tools', icon: 'notifications', children: null ,display:true},
    { path: 'events', title: 'Events', icon: 'event', children: null ,display:true},
  
  
  
  
  ];
  constructor(public settingsService: SettingsService,private loginService:LoginService) {
    this.menuItems = this.ROUTES;
    this.activeFontColor = 'rgba(0,0,0,.6)';
    this.normalFontColor = 'rgba(255,255,255,.8)';
    this.dividerBgColor = 'rgba(255, 255, 255, 0.5)';
  }

  ngOnInit() {
    this.color = this.settingsService.getSidebarFilter();
    this.settingsService.sidebarFilterUpdate.subscribe((filter: string) => {
      this.color = filter;
      if (filter === '#fff') {
        this.activeFontColor = 'rgba(0,0,0,.6)';
      }else {
        this.activeFontColor = 'rgba(255,255,255,.8)';
      }
    });
    this.settingsService.sidebarColorUpdate.subscribe((color: string) => {
      if (color === '#fff') {
        this.normalFontColor = 'rgba(0,0,0,.6)';
        this.dividerBgColor = 'rgba(0,0,0,.1)';
      }else {
        this.normalFontColor = 'rgba(255,255,255,.8)';
        this.dividerBgColor = 'rgba(255, 255, 255, 0.5)';
      }
    });
  }
  ngOnDestroy() {
    this.settingsService.sidebarFilterUpdate.unsubscribe();
    this.settingsService.sidebarColorUpdate.unsubscribe();
  }

  ngAfterViewInit() {
  }

  isAuthenticated()
  {
    return this.loginService.isAuthenticated();
  }
}
