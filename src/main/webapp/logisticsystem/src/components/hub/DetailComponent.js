import axios from "axios";
import React from "react";
import Cookies from "universal-cookie";
import "../account/css/style.css";
import HubMenu from "./HubMenuComponent"
const cookie = new Cookies();
axios.defaults.withCredentials = true;

const URL = "http://localhost:8080/account/detail.do";
class DetailComponent extends React.Component {
  constructor(props) {
    super(props);
    const cookies = new Cookies();
    this.state = {
      account: "",
      newpass: null,
      confirmpass: null,
      statuspass: "",
      staffid:
        cookies.get("hub_staff_id") ||
        cookies.get("driver_id") ||
        cookies.get("shipper_id") ||
        cookies.get("divider_id"),
    };
  }
  componentDidMount() {
    if (typeof cookie.get('hub_staff_id') == 'undefined')  window.location.assign('http://localhost:3000/exception/403');
    axios
      .post(
        URL,
        {
          staff_id: this.state.staffid,
        },
        {
          headers: {
            "content-type": "application/json",
          },
        }
      )
      .then((response) => {
        this.setState({ account: response.data });
      });
  }

  // state = {
  //     account : null,
  // };

  handlenew(e) {
    this.setState({ newpass: e.target.value });
  }

  handleconfirm(e) {
    this.setState({ confirmpass: e.target.value });
  }

  submit() {
    if (this.state.newpass === this.state.confirmpass) {
      const url = "http://localhost:8080/account/changepassword.do";
      const data = {
        staff_id: this.state.staffid,
        passwd: this.state.newpass,
      };
      axios
        .post(url, data, {
          headers: {
            "content-type": "application/json",
          },
        })
        .then(function (response) {
          window.location.reload();
        });
    } else {
      this.setState({
        statuspass: "New Password and Comfirmed New Password must be same",
      });
    }
  }

  render() {
    console.log("account" + this.state.account);
    return (
      <div className="container">
        <HubMenu />
        <h1 style={{ marginTop: "70px" }} className="text-center">
          ACCOUNT DETAIL
        </h1>

        <form className="form-left">
          <div className="form-group row">
            <label className="col-sm-2 col-form-label">Staff_id</label>
            <div className="col-sm-10">
              <input
                type="text"
                className="form-control"
                value={this.state.account.staff_id}
              ></input>
              {/* {this.state.account.staff_id} */}
            </div>
          </div>

          <div className="form-group row">
            <label className="col-sm-2 col-form-label">Name</label>
            <div className="col-sm-10">
              <input
                type="text"
                className="form-control"
                value={this.state.account.name}
              ></input>
            </div>
          </div>

          <div className="form-group row">
            <label className="col-sm-2 col-form-label">Phone_Number</label>
            <div className="col-sm-10">
              <input
                type="text"
                className="form-control"
                value={this.state.account.phone_num}
              ></input>
            </div>
          </div>

          <div className="form-group row">
            <label className="col-sm-2 col-form-label">Role</label>
            <div className="col-sm-10">
              <input
                type="text"
                className="form-control"
                value={this.state.account.role}
              ></input>
            </div>
          </div>
        </form>

        <form className="form-right">
          <div className="form-group row">
            <label className="col-sm-2 col-form-label">New_Password</label>
            <div className="col-sm-10">
              <input
                type="password"
                className="form-control"
                placeholder="Password"
                onChange={this.handlenew.bind(this)}
              ></input>
            </div>
          </div>

          <div className="form-group row">
            <label className="col-sm-2 col-form-label">
              Confirm_New_Password
            </label>
            <div className="col-sm-10">
              <input
                type="password"
                className="form-control"
                placeholder="Password"
                onChange={this.handleconfirm.bind(this)}
              ></input>
            </div>
          </div>

          <div>{this.state.statuspass}</div>
          <div className="form-group button box">
            <div className="col-sm-10">
              <button
                type="button"
                className="btn btn-primary"
                onClick={() => this.submit()}
              >
                Change_Password
              </button>
            </div>
          </div>
        </form>

        <div className="clear"></div>
      </div>
    );
  }
}

export default DetailComponent;
