import {Component, OnInit} from '@angular/core';
import {Router} from "@angular/router";

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit{

  constructor(private router: Router) { }

  ngOnInit(): void {
    this.addToggleFunctionality();
  }

  addToggleFunctionality(): void {

    const navBar = document.querySelector('nav');
    const avatarIcons = document.querySelectorAll('.avatar-circle');
    const overlay = document.querySelector('.overlay');

    if (!navBar || !overlay) {
      return;
    }

    avatarIcons.forEach(avatarIcon => {
      avatarIcon.addEventListener('click', () => {
        navBar.classList.toggle('open');
      });
    });

    overlay.addEventListener('click', () => {
      navBar.classList.remove('open');
    });
  }

  confirmLogout(): void {
    if (confirm('Are you sure you want to logout?')) {
      localStorage.removeItem('token');
      window.location.reload();

    }
  }

  closeSidebar(): void {
    const navBar = document.querySelector('nav');
    if (navBar) {
      navBar.classList.remove('open');
    }
  }
}
