import {Component, OnInit, ViewChild} from '@angular/core';
import {SlickCarouselComponent} from 'ngx-slick-carousel';
import {MatDialog} from '@angular/material/dialog';
import {DonateDialogComponent} from '@shared/components/donate-dialog/donate-dialog.component';
import {PrivacyPolicyComponent} from '@shared/components/privacy-policy/privacy-policy.component';

@Component({
  selector: 'app-landing',
  templateUrl: './landing.component.html',
  styleUrls: ['./landing.component.scss']
})
export class LandingComponent implements OnInit {
  showNav: boolean;
  @ViewChild('slickModalPhoto') slickModalPhoto: SlickCarouselComponent;
  slidePhotoCurrentIndex: number;
  slides = [
    {
      data:
        '          <div class="row text-left"> ' +
        '            <div class="col-lg-6 my-3">\n' +
        '              <h2 class="text-light-purple">What is FLAGexpress.ph?</h2>\n' +
        '              <p class="mx-2">\n' +
        '                   FLAGexpress.ph stands for The Frontliner Logistics Assistance Gateway. ' +
        '                   It is a system which allows individuals and stakeholders to report on status of donations' +
        '                   intended for our front-liners and other stakeholders involved in COVID-19 response. ' +
        '                   This is a project of the Office of Civil Defense (OCD) in partnership with the ' +
        '                   Department of Information and Communications Technology (DICT) for a transparent system for monitoring of donations.' +
        '              </p>\n' +
        '            </div>\n' +
        '            <div class="col-lg-6 my-3">\n' +
        '              <h2 class="text-light-purple">Who developed FLAGexpress.ph?</h2>\n' +
        '              <p class="mx-2">\n' +
        '                FLAGexpress.ph was developed by the DEVCON Community of Technology eXperts (DCTX) in collaboration with ' +
        '                the Office of Civil Defense (OCD) and the ' +
        '                Department of Information and Communications Technology (DICT).  DCTx provided the digital assistance to ' +
        '                realize this project. DCTx is a worldwide volunteer-based community of experts from various ' +
        '                fields who are developing digital solutions to alleviate the struggle of Filipino front-liners ' +
        '                against COVID-19.' +
        '              </p>\n' +
        '            </div>\n' +
        // '            <div class="col-lg-6 my-3">\n' +
        // '              <h2 class="text-light-purple">What does FLAGexpress.ph stand for</h2>\n' +
        // '              <p class="mx-2">\n' +
        // '                FLAGexpress.ph stands for Frontliner Logistics Assistance Gateway.' +
        // '              </p>\n' +
        // '            </div>\n' +
        '            <div class="col-lg-6 my-3">\n' +
        '              <h2 class="text-light-purple">Why was FLAGexpress.ph developed?\n</h2>\n' +
        '              <p class="mx-2">\n' +
        '                 FLAGexpress.ph was developed to help instill confidence that the intended ' +
        '                 donations reach the target recipients and help promote report accuracy and transparency.' +
        '              </p>\n' +
        '            </div>\n' +
        '           <div class="col-lg-6 my-3">\n' +
        '              <h2 class="text-light-purple">Is this a government initiative?</h2>\n' +
        '              <p class="mx-2">\n' +
        '               FLAGexpress.ph is a volunteer-driven project by DEVCON Community of Technology eXperts (DCTx)' +
        '               and managed by the Office of Civil Defense (OCD). This is a free-service private community initiative ' +
        '               without funding support from the government. ' +
        '              </p>\n' +
        '            </div>\n' +
        '          </div>'

    },
    {
      data:
        '          <div class="row text-left"> ' +
        '            <div class="col-lg-6 my-3">\n' +
        '              <h2 class="text-light-purple">Who can use FLAGexpress.ph?</h2>\n' +
        '              <p class="mx-2">\n' +
        '               Any individual or organization who would like to manifest a donation ' +
        '               addressed to the Philippine government may use FLAGexpress.ph. ' +
        '               Likewise, stakeholders can also use FLAGexpress.ph to report donations distributed ' +
        '               to targeted beneficiaries. ' +
        '              </p>\n' +
        '            </div>\n'
    },
  ];
  slideConfig = {
    dots: true,
    arrows: true,
    autoplay: true,
    infinite: true,
    centerMode: true,
    slidesToShow: 1,
    slidesToScroll: 1,
    autoplaySpeed: 3000,
    speed: 1000,
    responsive: [
      {
        breakpoint: 1024,
        settings: {
          slidesToShow: 1,
        }
      },
      {
        breakpoint: 980,
        settings: {
          slidesToShow: 1,
          arrows: false,
        }
      },
      {
        breakpoint: 600,
        settings: {
          slidesToShow: 1,
          arrows: false,
        }
      },
      {
        breakpoint: 480,
        settings: {
          slidesToShow: 1,
          arrows: false,
        }
      }
    ]
  };
  slidesPhoto = [
    { index: 0, img: 'assets/images/FLAGPic2.jpeg'},
    { index: 1, img: 'assets/images/FLAGPic3.jpeg'},
    { index: 2, img: 'assets/images/FLAGPic4.jpeg'},
    { index: 3, img: 'assets/images/FLAGPic5.jpeg'},
  ];
  slideConfigPhoto = {
    dots: false,
    arrows: false,
    autoplay: true,
    infinite: true,
    centerMode: true,
    slidesToShow: 3,
    slidesToScroll: 1,
    autoplaySpeed: 3000,
    speed: 1000,
    customPaging: (slider, i) => {
      const index = i + 1;
      return '<span class="dot">' + index + '</span>';
    },
    responsive: [
      {
        breakpoint: 1024,
        settings: {
          slidesToShow: 1,
        }
      },
      {
        breakpoint: 980,
        settings: {
          slidesToShow: 1,
          arrows: false,
        }
      },
      {
        breakpoint: 600,
        settings: {
          slidesToShow: 1,
          arrows: false,
        }
      },
      {
        breakpoint: 480,
        settings: {
          slidesToShow: 1,
          arrows: false,
        }
      }
    ]
  };
  constructor(
    public dialog: MatDialog) {
    this.slidePhotoCurrentIndex = 0;
    this.showNav = false;
  }

  ngOnInit(): void {
  }
  donate() {
    this.showNav = true;
    const fullScreenConfig = {
      minWidth: '100%',
      height: '100%',
      panelClass: 'custom-dialog-container',
    }
    const dialogRef = this.dialog.open(DonateDialogComponent, fullScreenConfig);
    dialogRef.afterClosed().subscribe((data: any) => {
      this.showNav = !data;
    });
  }

  showPrivacyPolicy(){
    this.showNav = true;
    const fullScreenConfig = {
      minWidth: '100%',
      height: '100%',
      panelClass: 'custom-dialog-container',
    }
    const dialogRef = this.dialog.open(PrivacyPolicyComponent, fullScreenConfig);
    dialogRef.afterClosed().subscribe((data: any) => {
      this.showNav = !data;
    });
  }

  page(event) {
    if (event === 'donate') {
        this.donate();
    } else {
      const x = document.getElementById(event);
      x.scrollIntoView({behavior: 'smooth'});
    }
  }
  beforeChange(e) {
    this.slidePhotoCurrentIndex = e.nextSlide;
  }
  nextPhoto() {
    this.slickModalPhoto.slickNext();
  }

  prevPhoto() {
    this.slickModalPhoto.slickPrev();
  }
}
