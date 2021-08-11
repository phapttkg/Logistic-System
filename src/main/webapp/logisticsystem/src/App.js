import HubHomeComponent from './components/hub/HubHomeComponent'
import HubImportListComponent from './components/hub/HubImportListComponent'
import HubExportListComponent from './components/hub/HubExportListComponent'
import HubPackageInHubComponent from './components/hub/HubPackageInHubComponent'
import HubShipperListComponent from './components/hub/HubShipperListComponent'
import DividerDriverListComponent from './components/divider/DividerDriverListComponent'
import DividerRoute from './components/divider/DividerRouteComponent'
import DriverHomeComponent from './components/driver/DriverHomeComponent'
import DriverPackageComponent from './components/driver/DriverPackageComponent'
import ShipperPackageComponent from './components/shipper/ShipperPackageComponent'
import ShipperHistoryComponent from './components/shipper/ShipperHistoryComponent'
import SignupComponent from './components/account/Sign_upComponent'
import SignOutComponent from './components/webflow/LogoutSucessComponent'
import EditAccountComponent from './components/account/EditAccountComponent'
import PageNotFoundComponent from './components/webflow/PageNotFoundComponent'
import ResourceNotFoundComponent from './components/webflow/ResourceNotFoundComponent'
import {BrowserRouter as Router, Route, Switch, Redirect  } from 'react-router-dom'
import HubDetailComponent from './components/hub/DetailComponent'
import ShipperDetailComponent from './components/shipper/DetailComponent'
import DividerDetailComponent from './components/divider/DetailComponent'
import DriverDetailComponent from './components/driver/DetailComponent'
import DriverHistoryComponent from './components/driver/DriverHistoryComponent'
import RedirectHome from './components/webflow/Redirect';

function App() {


  return (
      <Router>

          <Switch>
            <Route exact path = "/" component = {RedirectHome} ></Route>
            <Route exact path = "/hub/" component = {HubHomeComponent} ></Route>
            <Route exact path = "/hub/import"  component = {HubImportListComponent} ></Route>
            <Route exact path = "/hub/export"  component = {HubExportListComponent} ></Route>
            <Route exact path = "/hub/package"  component = {HubPackageInHubComponent} ></Route>
            <Route exact path = "/hub/shipper_list"  component = {HubShipperListComponent} ></Route>
            <Route exact path ="/divider/route" component ={DividerRoute}></Route>
            <Route exact path ="/divider/driver_list" component ={DividerDriverListComponent}></Route>
            <Route exact path ="/driver/" component ={DriverHomeComponent}></Route>
            <Route exact path = "/driver/container" component = {DriverPackageComponent}></Route>
            <Route exact path = "/account/sign-up" component = {SignupComponent}></Route>
            <Route exact path = "/account/edit" component = {EditAccountComponent}></Route>
            <Route exact path = "/signout" component = {SignOutComponent}></Route>
            <Route exact path ="/shipper/package" component ={ShipperPackageComponent}></Route>
            <Route exact path = "/driver/history" component = {DriverHistoryComponent}></Route>
            <Route exact path ="/shipper/history" component ={ShipperHistoryComponent}></Route>
            <Route exact path = "/hub/detail" component = {HubDetailComponent}></Route>
            <Route exact path = "/shipper/detail" component = {ShipperDetailComponent}></Route>
            <Route exact path = "/driver/detail" component = {DriverDetailComponent}></Route>
            <Route exact path = "/divider/detail" component = {DividerDetailComponent}></Route>
            <Route exact path = "/exception/404" component = {PageNotFoundComponent}></Route>
            <Route exact path = "/exception/403" component = {ResourceNotFoundComponent}></Route>
            <Route path='*' exact={true} component = {PageNotFoundComponent}></Route>
          </Switch>
      </Router>
  );
}

export default App;
