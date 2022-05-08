/**
 * This is a Swagger plugin that updates the base URL to the provided input URL.
 * As Swagger UI does not adjust the base URL correctly, this is a workaround based on the
 * suggestion in https://stackoverflow.com/a/71210264/339302.
 * Also see: https://swagger.io/docs/open-source-tools/swagger-ui/customization/plugin-api/
 */
const BaseUrlSettingPlugin = (system) => ({
  rootInjects: {
    setServer: (server) => {
      const jsonSpec = system.getState().toJSON().spec.json;
      const servers = [{url: server}];
      const newJsonSpec = Object.assign({}, jsonSpec, {servers});
      return system.specActions.updateJsonSpec(newJsonSpec);
    }
  }
});

window.onload = function () {
  const base_url = window.location.origin + window.location.pathname;
  const base_url_no_ending_slash = base_url.replace(/\/$/, '');
  window.ui = SwaggerUIBundle({
    url: `${base_url_no_ending_slash}/api/openapi.json`,
    dom_id: '#swagger-ui',
    deepLinking: true,
    withCredentials: true,
    presets: [
      SwaggerUIBundle.presets.apis,
      SwaggerUIStandalonePreset
    ],
    plugins: [
      SwaggerUIBundle.plugins.DownloadUrl,
      BaseUrlSettingPlugin
    ],
    layout: "StandaloneLayout",
    onComplete: () => {
      window.ui.setServer(base_url_no_ending_slash);
    }
  });
};
