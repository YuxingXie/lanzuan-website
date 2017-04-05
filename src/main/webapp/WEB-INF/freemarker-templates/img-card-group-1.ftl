    <div class="card-group m-x-0" ng-init="getpageComponent.varU()">
        <div class="card" ng-repeat="card in pageComponent.var.items">
            <div class="card-block padding-1">
                <a ng-if="card.link" ng-href="{{card.link}}">
                    <img ng-src="{{card.image}}" class="full-width "/>
                    <div class="sticker sticker-bottom p-b-05em large-110">{{card.text}}</div>
                </a>
                <div ng-if="!card.link">
                    <img ng-src="{{card.image}}" class="full-width "/>
                    <div class="sticker sticker-bottom p-b-05em large-110">{{card.text}}</div>
                </div>
            </div>
        </div>

    </div>


