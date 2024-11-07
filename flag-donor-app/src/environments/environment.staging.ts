export const environment = {
  production: false,
  apiUrl: window["env"]["apiUrl"] || 'https://flagexpress-api-stage.azurewebsites.net',
  debug: window["env"]["debug"] || false
};
