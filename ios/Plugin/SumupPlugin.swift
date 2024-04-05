import Foundation
import Capacitor

/**
 * Please read the Capacitor iOS Plugin Development Guide
 * here: https://capacitorjs.com/docs/plugins/ios
 */
<<<<<<< Updated upstream
@objc(SumupPlugin)
public class SumupPlugin: CAPPlugin {
=======
@objc(SumupPluginPlugin)
public class SumupPlugin: CAPPlugin {
    private let implementation = Sumup()

>>>>>>> Stashed changes
    @objc func echo(_ call: CAPPluginCall) {
        let value = call.getString("value") ?? ""
        call.resolve([
            "value": implementation.echo(value)
        ])
    }
}
