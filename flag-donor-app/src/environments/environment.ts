// This file can be replaced during build by using the `fileReplacements` array.
// `ng build --prod` replaces `environment.ts` with `environment.prod.ts`.
// The list of file replacements can be found in `angular.json`.

const apiUrl = 'http://23.97.51.15:8080'

export const environment = {
  production: false,
  //apiUrl: window["env"]["apiUrl"] || 'https://flagexpress-api-dev.azurewebsites.net',
  apiUrl,
  debug: window["env"]["debug"] || false,
  endpoints: {
    submitDonationForm: {
      url: `${apiUrl}/donationrequests`
    },
    getSectors: {
      url: `${apiUrl}/static/donorsectors`
    },
    getRecipientSectors: {
      url: `${apiUrl}/static/beneficiarysectors`
    },
    getCountries: {
      url: `${apiUrl}/static/countries`
    }
  }
};



/*
 * For easier debugging in development mode, you can import the following file
 * to ignore zone related error stack frames such as `zone.run`, `zoneDelegate.invokeTask`.
 *
 * This import should be commented out in production mode because it will have a negative impact
 * on performance if an error is thrown.
 */
// import 'zone.js/dist/zone-error';  // Included with Angular CLI.
