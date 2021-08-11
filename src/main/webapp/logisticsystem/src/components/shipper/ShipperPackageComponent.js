/*

@author: Huan 

*/

import React from "react";
import axios from "axios";
import ShipperMenu from "./ShipperMenuComponent";
import Cookies from "universal-cookie";
const cookie = new Cookies();

axios.defaults.withCredentials = true;

const SHIPPER_REST_API_URL = "http://localhost:8080/shipper/package";

class ShipperPackageComponent extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      packagelist: [],
      ispick: true,
    };
  }
  componentDidMount() {
    if (typeof cookie.get('shipper_id') == 'undefined')  window.location.assign('http://localhost:3000/exception/403');
    axios.get(SHIPPER_REST_API_URL).then((response) => {
      this.setState({ packagelist: response.data });

      console.log(response.data);
    });
  }

  //ship_route.map(route => route.packagelist.map(pkg => pkg.pkg_id))
  // this.state.packagelist.map(
  handlesummitpick(e) {
    const URL = "http://localhost:8080/shipper/pick.do";
    axios.post(URL, { pkg_id: e.target.value }).then(function (response) {
      window.location.reload();
    });
  }
  handlesummitdrop(e) {
    const URL = "http://localhost:8080/shipper/drop.do";
    axios.post(URL, { pkg_id: e.target.value }).then(function (response) {
      window.location.reload();
    });
  }
  handlesummitimport(e) {
    const URL = "http://localhost:8080/shipper/import.do";
    axios.post(URL, { pkg_id: e.target.value }).then(function (response) {
      window.location.reload();
    });
  }
  handlesummitexport(e) {
    const URL = "http://localhost:8080/shipper/export.do";
    axios.post(URL, { pkg_id: e.target.value }).then(function (response) {
      window.location.reload();
    });
  }
  handlesummitcancel(e) {
    const URL = "http://localhost:8080/shipper/cancel.do";
    axios.post(URL, { pkg_id: e.target.value }).then(function (response) {
      window.location.reload();
    });
  }
  handlesummitreturn(e) {
    const URL = "http://localhost:8080/shipper/return.do";
    axios.post(URL, { pkg_id: e.target.value }).then(function (response) {
      window.location.reload();
    });
  }

  render() {
    return (
      <div className="container">
        <ShipperMenu />
        <h1
          className="mx-auto text-center"
          style={{ marginTop: "70px", padding: "30px" }}
        >
          Package List To Day
        </h1>
        <div id="accordion2" className="row">
          {this.state.packagelist.map((pkg) => (
            <div class="col-12 ">
              <div
                className=" shadow-lg bg-gray between"
                key={"sk" + pkg.pkg_id}

              >
                <div className="card-header" id={"heading" + pkg.pkg_id}>
                  <h5 className="mb-0">
                    {pkg.next_hub === "final" ? (
                      <div class="d-flex justify-content-around" >
                        <button
                          className="btn btn-primary mr-2"
                          data-toggle="collapse"
                          data-target={"#" + pkg.pkg_id}
                          aria-expanded="true"
                          aria-controls={pkg.pkg_id}
                          style={{ width: "22%" }}
                        >
                          PackageID: {pkg.pkg_id}
                        </button>
                        {(pkg.current_hub === "-1" && !splitLastStatus(pkg.tracking_status).includes('return')) 
                        ? 
                        (
                          <button
                            type="button"
                            class="btn btn-success mx-2"
                            value={pkg.pkg_id}
                            onClick={this.handlesummitimport.bind(this)}
                            style={{ width: "22%" }}
                          >
                          {splitLastStatus(pkg.tracking_status).includes('sending') ? "Import" : "Done"}
                          </button>)
                          :""}
                        {(pkg.current_hub === "-1" && !splitLastStatus(pkg.tracking_status).includes('return')) 
                        ? 
                        (
                          <button
                            type="button"
                            class="btn btn-danger mx-2"
                            value={pkg.pkg_id}
                            onClick={this.handlesummitdrop.bind(this)}
                            style={{ width: "22%" }}
                          >
                            {pkg.tracking_status.indexOf("delivered") === -1
                              ? "Drop"
                              : "Done"}
                          </button>)
                          :""}

                          {(!splitLastStatus(pkg.tracking_status).includes('return') && pkg.current_hub !== '-1')
                          ?
                          <button type="button" class="btn btn-secondary" style={{ width: "22%" }}>
                            NOT EXPORT YET
                          </button>
                          :""}

                        {(!splitLastStatus(pkg.tracking_status).includes('delivered') && pkg.current_hub === "-1")
                        ?
                        <button
                          type="button"
                          class="btn btn-warning"
                          value={pkg.pkg_id}
                          onClick={this.handlesummitreturn.bind(this)}
                          style={{ width: "22%" }}
                        >
                          {pkg.tracking_status.indexOf("return") === -1
                            ? "Return"
                            : "Done"}
                        </button>
                        :""}
                      </div>
                    ) : (
                      <div class="d-flex justify-content-between">
                        <button
                          className="btn btn-primary mx-2"
                          data-toggle="collapse"
                          data-target={"#" + pkg.pkg_id}
                          aria-expanded="true"
                          aria-controls={pkg.pkg_id}
                          style={{ width: "35%" }}
                        >
                          PackageID: {pkg.pkg_id}
                        </button>
                        {(!splitLastStatus(pkg.tracking_status).includes('cancel'))
                        ?
                        <button
                          type="button"
                          class="btn btn-success"
                          value={pkg.pkg_id}
                          onClick={this.handlesummitpick.bind(this)}
                          style={{ width: "23%" }}
                        >
                          {(pkg.tracking_status.indexOf("picked") === -1)
                            ? "Pick"
                            : "Done"}
                        </button>
                        :""}
                        {(!splitLastStatus(pkg.tracking_status).includes('cancel'))
                        ?
                        <button
                          type="button"
                          class="btn btn-danger mx-2"
                          value={pkg.pkg_id}
                          onClick={this.handlesummitexport.bind(this)}
                          style={{ width: "23%" }}
                        >
                          {(pkg.current_shipper === -1 && splitLastStatus(pkg.tracking_status).includes('picked')) ? "Done" : "Export"}
                        </button>
                        :""}
                        {(!splitLastStatus(pkg.tracking_status).includes('picked'))
                        ?
                        <button
                          type="button"
                          class="btn btn-warning"
                          value={pkg.pkg_id}
                          onClick={this.handlesummitcancel.bind(this)}
                          style={{ width: "23%" }}
                        >
                          {pkg.tracking_status.indexOf("cancel") === -1
                            ? "Cancel"
                            : "Done"}
                        </button>
                        :""}
                      </div>
                    )}
                  </h5>
                </div>

                <div
                  id={pkg.pkg_id}
                  className="collapse"
                  aria-labelledby={"heading" + pkg.pkg_id}
                  data-parent="#accordion"
                >
                  <div className="card-body">
                    <p>
                      <b>Packge ID:</b> {pkg.pkg_id}
                    </p>
                    <hr />
                    <p>
                      <b>Created Time:</b> {pkg.created_datetime}
                    </p>
                    <hr />{" "}
                    <p>
                      <b>Delivery Fee: </b>
                      {pkg.delivery_fee}
                    </p>
                    <hr />{" "}
                    <p>
                      <b>COD Value: </b>
                      {pkg.cod_value}
                    </p>
                    <hr />{" "}
                    <p>
                      <b>Receiver Name:</b> {pkg.receiver_name}
                    </p>
                    <hr />
                    <p>
                      <b>Receiver Phone Number:</b> {pkg.receiver_phone_num}
                    </p>
                    <hr />{" "}
                    <p>
                      <b>Receiver Address: </b>
                      {pkg.receiver_address}
                    </p>
                    <hr />{" "}
                    <p>
                      <b>Sender Name: </b>
                      {pkg.sender_name}
                    </p>
                    <hr />{" "}
                    <p>
                      <b>Sender Phone Number:</b> {pkg.sender_phone_num}
                    </p>
                  </div>
                </div>
              </div>
            </div>
          ))}
        </div>
      </div>
    );
  }
}

const splitLastStatus = function (status) {
  const splitStatus = status.split("-");
  const index = splitStatus.length - 1;
  return splitStatus[index];
};

export default ShipperPackageComponent;
