# GBIF-API-Tools

This app provides Web-UI-based tools to view and request data from the [Global Biodiversity
Information Facility](https://www.gbif.org/). It is a personal project and not affiliated to
GBIF. Feel free to send PRs and ideas.

## Development

The app exposes a GraphQL-API that will be publicly accessible.

### Backbone Tree

This feature will gather the Taxonomy Backbone into a seperate local dataset to make it
accessible in a clickable tree-shaped UI. The nodes will provide links back to direct GBIF-API
requests or the GBIF-UI.