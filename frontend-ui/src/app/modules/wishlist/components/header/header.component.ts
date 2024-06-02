import {Component, OnInit} from '@angular/core';


@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrl: './header.component.css'
})
export class HeaderComponent implements OnInit{

  constructor() { }

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
